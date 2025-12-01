<h1>ProInova API</h1>
<p>Breve resumo sobre a api e seus endpoints</p>

<h1>/auth/**</h1>
<p><strong>/register</strong> endpoint de criar usuario, recebe body dessa forma:
{
    "username": "usuario",
    "email": "usuario@gmail.com",
    "name": "Nome Completo",
    "password": "senha",
    "confirmPassword": "senhaconfirmacao"
}

  e retorna o token JWT de forma
  {
    "token":"token"
  }
</p>
<p><strong>/login</strong> endpoint de logar, recebe body dessa forma:
{
  "email": "usuario@gmail.com",
  "senha": "senha"
}
  tambem retorna token da mesma forma
</p>
<p><strong>/me</strong> endpoint que retorna os dados do usuario logado excluindo senha, precisa do token JWT para receber usuario autenticado</p>
