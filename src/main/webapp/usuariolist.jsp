<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Usuários</title>
</head>
<body>
    <h2>Lista de Usuários</h2>
    <a href="UsuarioServlet?action=new">Criar Novo Usuário</a>
    <hr>
    <c:if test="${not empty usuarios}">
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <td>${usuario.id}</td>
                    <td>${usuario.nome}</td>
                    <td>${usuario.email}</td>
                    <td>
                        <a href="UsuarioServlet?action=edit&id=${usuario.id}">Editar</a> |
                        <a href="UsuarioServlet?action=delete&id=${usuario.id}" onclick="return confirm('Tem certeza que deseja excluir este usuário?');">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty usuarios}">
        <p>Nenhum usuário encontrado.</p>
    </c:if>
</body>
</html>