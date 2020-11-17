# Student Card

Criado projeto de uma empresa (FIAP) que está ingressando no segmento de cartões de crédito para fornecer o serviço aos seus alunos.

## Justificativas da composição do projeto

A linguagem escolhida para o desenvolvimento foi: <h1>Kotlin</h1>.
Foram utilizados diversos módulo do framework Spring
Foram criadas as duas filas abaixo no Rabbit MQ:

- drone.allinfo: fila alimentada pelo front-end para o consumo do microserviço A responsável pelo envio de alertas por e-mail;
- drone.locationInfo: fila alimentada pelo front-end somente quando a opção de rastreamento está ativa e consumida pelo microserviço B responsável pelo rastreamento.

Foi utilizada a estrategia direct exchange na fila Rabbit MQ para o envio das mensagens entre o front e os microservicos, pois a solucao foi pensada para a utilizacao de roteamento unicast.

Para facilitar e melhorar a produtividade no back-end foi utilizado o spring framework e no front-end o NextJS.
A documentaçao da API do servico de monitoramento foi feita atraves do swagger.


## Justificativas da composição do projeto

## Frontend (pasta agro-drone)

Para iniciar o projeto são necessários alguns passos:

- Executar comando: **npm install** (instalar as dependecias do projeto)
- Criar a variável de ambiente **CLOUDAMQP_URL** com a url da instância criada no https://www.cloudamqp.com/

    Para criar uma instância é necessário criar uma conta pelo link https://customer.cloudamqp.com/signup, feito isso o site irá te enviar para url https://customer.cloudamqp.com/instance e com isso podemos criá-la.
    
    Após criar a instância ela aparecerá na sua lista, clicando encima conseguimos ver as informações detalhadas e lá terá a AMQP URL que deve ser colocada na variável de ambiente.
    
- Criar exchange, binds e queues que serão utilizadas

    Para criar é necessário entrar no RabbitMQ Manager da sua instância. 
    
    Vamos criar o Exchange, para isso clicar na aba Exchanges e logo abaixo terá uma sessão **Add new exchange**, no nome colocar **exchange.drone** o restante é só deixar como está, feito isso é só clicar em **Add Exchange**.
    
    Para criar as queues entraremos na aba Queues e logo abaixo terá a sessão **Add a new queue**, como vamos criar duas o processo precisará ser repetido uma segunda vez, para primeira queue no nome colocar **drone.allinfo** (Fila que será consumida pelo microserviço A) os demais parâmetros ficam como estão, feito isso clicar em **Add queue** e está criada, para a segunda queue o nome será **drone.locationInfo** (Fila que será consumida pelo microserviço B) repetindo os passos acima.
    
    Com tudo criado, precisamos configurar nossos Binds com as routingKeys e suas respectivas filas, para fazer isso na aba Exchange clicar no **exchange.drone** que foi criado e na sessão **Bind > Add binding from this exchange** criar os seguintes binds:
    - to_queue: drone.allinfo, RoutingKey: allinfo, arguments não mexer; (Após preencher clicar no botão **Bind**)
    - to_queue: drone.locationInfo, RoutingKey: locationinfo, arguments não mexer; (Após preencher clicar no botão **Bind**)
    
    Feito todos esses passos nossa configuração da menssageria está feita.
    
- Para rodar o projeto frontend temos duas maneiras, para rodar local em modo de desenvolvimento executar o comando **npm run dev**, para rodar com uma versão pronta para deploy em ambientes executar os comandos **npm run build** para gerar o build do projeto e **npm start**, em ambos os casos o projeto erá subir na url http://localhost:3000/

### Observações

- Ambas as queues somente serão alimentadas se todas as informações do formulario forem preenchidas (ID Drone, Latitude, Longitude) e válidas (latitude, longitude);
- A queue drone.allinfo será alimentada de 10 em 10 segundos;
- A queue drone.locationInfo será alimentada somente se o botão de ativar rastreamento for ativado sendo o intervalo de 10 em 10 segundos;

## Backend Microservico A (pasta microservico-A)

O **microservico-a** é um microserviço responsável pelo envio de e-mail com os alertas dos drones de acordo com as métricas definidas pelo porfessor, sendo elas:

- Enviar um e-mail se em 1 minuto a temperatura do drone registrada for: (Temperatura >= 35 ou <= 0) OU (Umidade do ar <= 15%).

Este microservico visualiza a fila drone.allinfo do RabbitMQ e verifica se é necessário enviar um alerta por e-mail.
Caso haja a necessidade o e-mail é enviado através do Gmail.

### Inicialização

A inicialização é feita de maneira simplificada pelo uso do spring boot sendo necessário apenas executar a classe **SendEmailApplication**. Contudo, para que ocorra o correto funcionamento do microsserviço é necessário o preenchimento das propriedades no arquivo **application.yaml** neste arquivo temos diversas informações de credenciais e configurações do sistema, sendo elas (as principais):<br/>

credentials.email : conta de e-mail do Gmail.<br/>
credentials.password: Senha de acesso do e-mail.<br/>
mail.smtp.emailDestination: E-mail de destino do alerta no caso do usuário não preencher um e-mail no front-end;<br/>
mail.smtp.attachPath: Caminho do arquivo temporário que será criado com o mapa usado como anexo no e-mail. Ex: /var/tmp/test.png<br/>
api.googlemaps: Key gerada para acesso a API do Google Maps;<br/>
rabbitmq.username: Nome do usuário da conta do Rabbit MQ;<br/>
rabbitmq.virtualhost: Nome do host da conta do Rabbit MQ;<br/>
rabbitmq.password: Senha da conta do Rabbit MQ;<br/>
rabbitmq.url: URL de acesso do Rabbit MQ. Ex: bunny.cloudamqp.com<br/>
rabbitmq.queue: Fila do Rabbit MQ. Ex: drone.allinfo<br/>
rabbitmq.exchange: Exchange do Rabbit MQ. Ex: exchange.drone<br/>

Para habilitar o envio de e-mail no Gmail é necessário habilitar a autenticação de Aplicativos. Conforme o link abaixo:

https://myaccount.google.com/signinoptions/two-step-verification/enroll-welcome

Para criar a chave de API do Google Maps você pode seguir o link abaixo:

https://developers.google.com/places/web-service/get-api-key

## Backend Microservico B (pasta localiza-drone)

   O **localiza-done** é um microsserviço responsável pela exibição da localização do drone de maneira visual no mapa. Através de um serviço node(agro-drone), quando a opção de ratreamento está ativada, ocorre o envio de mensagens para a fila  **drone.locationInfo** (RabbitMQ) que são consumidas permitindo a construção dinâmica do mapa.
   
### Inicialização   
  A inicialização é feita de maneira simplificada pelo uso do spring boot sendo necessário apenas executar a classe **LocalizaDroneApplication**. Contudo, para que ocorra o correto funcionamento do microsserviço é necessário o preenchimento da propriedade **google.key** a fim de a aplicação conseguir autenticar no serviço de mapas do google. 
  O mapa possúi uma coordenada padrão para que seja renderizado pela primeira vez que é recuperada das propriedades **drone.lat** e **drone.lng**. Dessa forma, caso haja a necessidade de alteração das coordenadas de inicialização basta apenas alterar seus valores no arquivo **applications.properties** localizada a pasta resource do projeto.  
  
### Documentação
  As informações referentes as rotas e funcionalidades do microsserviço podem ser acessadas através do swagger(http://localhost:8080/localiza-drone/swagger-ui.html) configurado no projeto.
