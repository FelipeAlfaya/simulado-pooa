<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Página Inicial</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Bem-vindo ao Sistema de Gerenciamento de Usuários</h1>
    <p>Utilize os links abaixo para navegar pelo sistema:</p>
    <ul>
        <li><a href="UsuarioServlet?action=list">Listar Usuários</a></li>
        <li><a href="UsuarioServlet?action=new">Criar Novo Usuário</a></li>
    </ul>
</body>
</html>