<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title><c:choose><c:when test="${not empty usuario}">Editar Usuário</c:when><c:otherwise>Criar Usuário</c:otherwise></c:choose></title>
</head>
<body>
    <h2>
        <c:choose>
            <c:when test="${not empty usuario}">Editar Usuário</c:when>
            <c:otherwise>Criar Usuário</c:otherwise>
        </c:choose>
    </h2>
    <form action="UsuarioServlet" method="post">
        <input type="hidden" name="action" value="<c:choose><c:when test='${not empty usuario}'>update</c:when><c:otherwise>create</c:otherwise></c:choose>">
        <c:if test="${not empty usuario}">
            <input type="hidden" name="id" value="${usuario.id}">
        </c:if>
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="${usuario.nome}" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${usuario.email}" required><br><br>
        <label for="papelId">Papel:</label>
        <select id="papelId" name="papelId">
            <c:forEach var="papel" items="${papeis}">
                <option value="${papel.id}" <c:if test="${not empty usuario && usuario.papel.id == papel.id}">selected</c:if>>
                    ${papel.nome}
                </option>
            </c:forEach>
        </select><br><br>
        <button type="submit">
            <c:choose>
                <c:when test="${not empty usuario}">Salvar Alterações</c:when>
                <c:otherwise>Criar Usuário</c:otherwise>
            </c:choose>
        </button>
    </form>
</body>
</html>