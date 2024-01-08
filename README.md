<h1 align="center">Api Spring-Boot em Container - CRUD de Imagens com Autenticação</h1> 

<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" alt="java logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" height="40" alt="spring logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" height="40" alt="postgresql logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" height="40" alt="docker logo"  />
</div>


<p align="left">Esta API Spring Boot é projetada para fornecer funcionalidades CRUD (Create, Read, Update, Delete) para gerenciamento de imagens. O sistema permite que os usuários autenticados realizem operações de criação, leitura, atualização e exclusão de imagens, com suporte à autenticação usando o Spring Security e geração de tokens JWT (JSON Web Token).</p>
<p aling="left"> As imagens são persistidas em um banco de dados PostgreSQL, que é executado dentro de um container Docker.
  Essa abordagem oferece uma solução eficiente para armazenar e gerenciar dados de imagens de forma escalável e consistente.</p>


<h2 align="center">Recursos Principais</h2>

<h3 align="left">Autenticação e Autorização:</h3>

 <ul>
      <li>A API utiliza o Spring Security para autenticação e autorização.</li>
      <li>O processo de autenticação é baseado em tokens JWT, proporcionando um meio seguro e escalável de gerenciar sessões de usuário.</li>
 </ul>
 
</ul>
<h3 aling="left">Endpoints CRUD de Imagens:</h3>
    <ul>
      <li><strong>GET /v1/images</strong>: Endpoint para recuperar a lista de todas as imagens disponíveis.</li>
      <li><strong>GET  /v1/images/:id</strong>: Endpoint para obter detalhes específicos de uma imagem.</li>
      <li><strong>POST /v1/images</strong>: Endpoint para enviar uma nova imagem ao sistema.</li>
      <li><strong>POST /v1/users</strong>: Endpoint para cadastro de novos usuarios.</li>
      <li><strong>POST /v1/users/auth </strong>: Endpoint realizar a autenticação e recuperar o JWT token.</li>
    </ul>
 
 <h3 aling="left">Gerenciamento de Containers com Docker:</h3>
    <ul>
      <li>A aplicação é empacotada e distribuída em containers Docker para facilitar a implantação e garantir a consistência do ambiente.</li>
    </ul>
    
<h3 aling="left">Build e Execução com Docker:</h3>

<h4>Docker Compose</h4>

<p>O arquivo <code>docker-compose.yml</code> define a configuração do Docker Compose para orquestrar os serviços necessários para a aplicação de gerenciamento de imagens.</p>

<h3>Serviços</h3>

<dl>

  <dt>Banco de Dados (db)</dt>
  <dd>
    <ul>
      <li>Imagem: <code>postgres</code></li>
      <li>Porta Exposta: <code>5434:5432</code></li>
      <li>Credenciais:
        <ul>
          <li>Usuário: <code>postgres</code></li>
          <li>Senha: <code>postgres</code></li>
          <li>Banco de Dados: <code>imagens</code></li>
        </ul>
      </li>
      <li>Volume: <code>./data-container</code> para persistência dos dados.</li>
      <li>Rede: <code>imagesapinet</code></li>
    </ul>
  </dd>

  <dt>pgAdmin</dt>
  <dd>
    <ul>
      <li>Imagem: <code>dpage/pgadmin4</code></li>
      <li>Porta Exposta: <code>15434:80</code></li>
      <li>Credenciais:
        <ul>
          <li>Usuário: <code>admin</code></li>
          <li>Senha: <code>admin@admin.com</code></li>
        </ul>
      </li>
      <li>Dependência: Banco de Dados (<code>db</code>)</li>
      <li>Rede: <code>imagesapinet</code></li>
    </ul>
  </dd>

  <dt>API Spring Boot (sbootapi)</dt>
  <dd>
    <ul>
      <li>Construída a partir do Dockerfile no contexto atual.</li>
      <li>Nome do Contêiner: <code>imagesapi</code></li>
      <li>Porta Exposta: <code>8080:8080</code></li>
      <li>Dependência: Banco de Dados (<code>db</code>)</li>
      <li>Variável de Ambiente: <code>POSTGRES_HOST=db</code></li>
      <li>Rede: <code>imagesapinet</code></li>
    </ul>
  </dd>

</dl>

<h3>Rede</h3>

<ul>
  <li>Nome: <code>imagesapinet</code></li>
  <li>Driver: <code>bridge</code></li>
</ul>

<p>Este arquivo Docker Compose simplifica o processo de execução da aplicação e serviços relacionados, permitindo que você os inicie simultaneamente com uma única instrução.</p>
