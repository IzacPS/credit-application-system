<h1>request-credit-system</h1>
<p align="center">API Rest para um Sistema de Analise de Solicitação de Crédito</p>
<p align="center">Baseado no <a href="https://github.com/cami-la/credit-application-system/tree/master">desafio de projeto</a> da 
plataforma <a href="https://dio.me">Digital Innovation One (DIO)</a>.</p>
<p align="center">
     <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v17-blue.svg" />
    </a>
    <a alt="Kotlin">
        <img src="https://img.shields.io/badge/Kotlin-v1.7.22-purple.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v3.0.3-brightgreen.svg" />
    </a>
    <a alt="Gradle">
        <img src="https://img.shields.io/badge/Gradle-v7.6-lightgreen.svg" />
    </a>
    <a alt="H2 ">
        <img src="https://img.shields.io/badge/H2-v2.1.214-darkblue.svg" />
    </a>
    <a alt="Flyway">
        <img src="https://img.shields.io/badge/Flyway-v9.5.1-red.svg">
    </a>
</p>

<p>Uma empresa de empréstimo precisa criar um sistema de análise de solicitação de crédito. Sua tarefa será criar uma <strong>API REST SPRING BOOT E KOTLIN</strong> para a empresa fornecer aos seus clientes as seguintes funcionalidades:</p>

<ul>
<li><h3>Cliente (Customer):</h3>
  <ul>
    <li><strong>Cadastrar:</strong>
         <ol>
            <li><strong>Request: </strong><em>firstName, lastName, cpf, income, email, password, zipCode e street</em></li>
            <li><strong>Response: </strong><em>String</em></li>
        </ol>
    </li>
  <li><strong>Editar cadastro:</strong>
    <ol>
      <li><strong>Request: </strong><em>id, firstName, lastName, income, zipCode, street</em></li>
      <li><strong>Response: </strong><em>firstName, lastName, income, cpf, email, income, zipCode, street</em></li>
    </ol>
  </li>  
  <li><strong>Visualizar perfil:</strong>
    <ol>
      <li><strong>Request: </strong> <em>id</em></li>
      <li><strong>Response: </strong><em>firstName, lastName, income, cpf, email, income, zipCode, street</em></li>
    </ol> 
  </li>
  <li><strong>Deletar cadastro:</strong>
    <ol>
      <li><strong>Request: </strong><em>id</em></li>
      <li><strong>Response: </strong><em>sem retorno</em></li>
    </ol>
  </li>
  </ul>
  </li>
  <li><h3>Solicitação de Empréstimo (Credit):</h3>
  <ul>
    <li><strong>Cadastrar:</strong>
         <ol>
            <li><strong>Request: </strong><em>creditValue, dayFirstOfInstallment, numberOfInstallments e customerId</em></li>
            <li><strong>Response: </strong><em>String</em></li>
        </ol>
    </li>
    <li><strong>Listar todas as solicitações de emprestimo de um cliente:</strong>
    <ol>
      <li><strong>Request: </strong><em>customerId</em></li>
      <li><strong>Response: </strong><em>creditCode, creditValue, numberOfInstallment</em></li>
    </ol> 
    </li>
    <li><strong>Visualizar um emprestimo:</strong>
    <ol>
      <li><strong>Request: </strong><em>customerId e creditCode</em></li>
      <li><strong>Response: </strong><em>creditCode, creditValue, numberOfInstallment, status, emailCustomer e incomeCustomer</em></li>
    </ol> 
    </li>
  </ul>
</ul>


<figure>
<p align="center">
  <img src="https://i.imgur.com/7phya16.png" height="450" width="650" alt="API para Sistema de Avaliação de Créditos"/><br>
  Diagrama UML Simplificado de uma API para Sistema de Avaliação de Crédito
</p>
</figure>
<figure>
<p align="center">
  <img src="https://i.imgur.com/1Ea5PH3.png" height="350" width="600" alt="Arquitetura em 3 camadas Projeto Spring Boot"/><br>
  Arquitetura em 3 camadas Projeto Spring Boot
</p>
</figure>

<hr>
<h3>Links Úteis</h3>
<ul>
  <li>https://start.spring.io/#!type=gradle-project&language=kotlin&platformVersion=3.0.3&packaging=jar&jvmVersion=17&groupId=me.dio&artifactId=credit-application-system&name=credit-application-system&description=Credit%20Application%20System%20with%20Spring%20Boot%20and%20Kotlin&packageName=me.dio.credit-application-system&dependencies=web,validation,data-jpa,flyway,h2</li>
  <li>https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/common-application-properties.html</li>
  <li>https://medium.com/cwi-software/versionar-sua-base-de-dados-com-spring-boot-e-flyway-be4081ddc7e5</li>
  <li>https://strn.com.br/artigos/2018/12/11/todas-as-anota%C3%A7%C3%B5es-do-jpa-anota%C3%A7%C3%B5es-de-mapeamento/</li>
  <li>https://pt.wikipedia.org/wiki/Objeto_de_Transfer%C3%AAncia_de_Dados</li>
  <li>https://pt.wikipedia.org/wiki/CRUD</li>
  <li>https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords</li>
  <li>https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query</li>
  <li>https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#glossary</li>  
</ul>