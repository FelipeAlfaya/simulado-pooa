<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Usu�rios</title>
</head>
<body>
    <h2>Lista de Usu�rios</h2>
    <a href="UsuarioServlet?action=new">Criar Novo Usu�rio</a>
    <hr>
    <c:if test="${not empty usuarios}">
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>A��es</th>
            </tr>
            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <td>${usuario.id}</td>
                    <td>${usuario.nome}</td>
                    <td>${usuario.email}</td>
                    <td>
                        <a href="UsuarioServlet?action=edit&id=${usuario.id}">Editar</a> |
                        <a href="UsuarioServlet?action=delete&id=${usuario.id}" onclick="return confirm('Tem certeza que deseja excluir este usu�rio?');">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty usuarios}">
        <p>Nenhum usu�rio encontrado.</p>
    </c:if>
</body>
</html>