<%@ page import="java.util.ArrayList" %>
<%@ page import="com.veterinaria.beans.Mascota" %>
<%@ page import="com.veterinaria.beans.Especie" %>
<%@ page import="com.veterinaria.beans.Veterinario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
ArrayList<Mascota> lista = (ArrayList<Mascota>) request.getAttribute("lista");
ArrayList<Especie> especies = (ArrayList<Especie>) request.getAttribute("listaEspecies");
ArrayList<Veterinario> veterinarios = (ArrayList<Veterinario>) request.getAttribute("listaVeterinarios");
String especieSel = (String) request.getAttribute("especieSeleccionada");
String veterinarioSel = (String) request.getAttribute("veterinarioSeleccionado");
%>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Lista de mascotas</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body><div class="container mt-4">
<h1>Lista de Mascotas</h1>
<a class="btn btn-success mb-3" href="<%=request.getContextPath()%>/MascotaServlet?action=formCrear">Nueva Mascota</a>
<form class="row g-2 mb-3" method="get" action="<%=request.getContextPath()%>/MascotaServlet">
<input type="hidden" name="action" value="lista">
<div class="col-md-4"><label>Filtrar por especie</label><select class="form-select" name="especieId">
<option value="">Todas las especies</option>
<% for (Especie e : especies) { %>
<option value="<%=e.getIdespecie()%>" <%=especieSel != null && especieSel.equals(String.valueOf(e.getIdespecie())) ? "selected" : ""%>><%=e.getNombre()%></option>
<% } %></select></div>
<div class="col-md-4"><label>Filtrar por veterinario</label><select class="form-select" name="veterinarioId">
<option value="">Todos los veterinarios</option>
<% for (Veterinario v : veterinarios) { %>
<option value="<%=v.getIdveterinario()%>" <%=veterinarioSel != null && veterinarioSel.equals(String.valueOf(v.getIdveterinario())) ? "selected" : ""%>><%=v.getNombre()%></option>
<% } %></select></div>
<div class="col-md-4 align-self-end"><button class="btn btn-primary">Filtrar</button> <a class="btn btn-secondary" href="<%=request.getContextPath()%>/MascotaServlet">Limpiar</a></div>
</form>
<table class="table table-striped table-bordered"><thead class="table-success"><tr><th>ID</th><th>Nombre</th><th>Edad</th><th>Peso</th><th>Especie</th><th>Veterinario</th><th>Dueño</th><th>Acción</th></tr></thead><tbody>
<% if (lista != null && !lista.isEmpty()) { for (Mascota m : lista) { %>
<tr><td><%=m.getIdmascota()%></td><td><%=m.getNombre()%></td><td><%=m.getEdad()%></td><td><%=m.getPeso()%></td><td><%=m.getEspecie().getNombre()%></td><td><%=m.getVeterinario().getNombre()%></td><td><%=m.getDueno().getNombre()%></td>
<td><form method="post" action="<%=request.getContextPath()%>/MascotaServlet" onsubmit="return confirm('¿Borrar esta mascota?');">
<input type="hidden" name="action" value="borrar">
<input type="hidden" name="id" value="<%=m.getIdmascota()%>">
<button class="btn btn-danger btn-sm" type="submit">Borrar</button>
</form></td></tr>
<% } } else { %><tr><td colspan="8">No hay mascotas para mostrar.</td></tr><% } %>
</tbody></table></div></body></html>