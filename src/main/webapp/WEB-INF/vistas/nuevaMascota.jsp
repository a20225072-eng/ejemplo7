<%@ page import="java.util.ArrayList" %>
<%@ page import="com.veterinaria.beans.Especie" %>
<%@ page import="com.veterinaria.beans.Veterinario" %>
<%@ page import="com.veterinaria.beans.Dueno" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
ArrayList<Especie> especies = (ArrayList<Especie>) request.getAttribute("listaEspecies");
ArrayList<Veterinario> veterinarios = (ArrayList<Veterinario>) request.getAttribute("listaVeterinarios");
ArrayList<Dueno> duenos = (ArrayList<Dueno>) request.getAttribute("listaDuenos");
%>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Nueva mascota</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body><div class="container mt-4" style="max-width:650px"><h1>Nueva Mascota</h1>
<form method="post" action="<%=request.getContextPath()%>/MascotaServlet">
<input type="hidden" name="action" value="crear">
<div class="mb-3"><label>Nombre</label><input class="form-control" name="nombre" required></div>
<div class="mb-3"><label>Edad</label><input class="form-control" type="number" name="edad" min="0" required></div>
<div class="mb-3"><label>Peso (kg)</label><input class="form-control" type="number" name="peso" min="0.01" step="0.01" required></div>
<div class="mb-3"><label>Especie</label><select class="form-select" name="especieId" required><% for (Especie e : especies) { %><option value="<%=e.getIdespecie()%>"><%=e.getNombre()%></option><% } %></select></div>
<div class="mb-3"><label>Veterinario</label><select class="form-select" name="veterinarioId" required><% for (Veterinario v : veterinarios) { %><option value="<%=v.getIdveterinario()%>"><%=v.getNombre()%></option><% } %></select></div>
<div class="mb-3"><label>Dueño</label><select class="form-select" name="duenoId" required><% for (Dueno d : duenos) { %><option value="<%=d.getIddueno()%>"><%=d.getNombre()%></option><% } %></select></div>
<button class="btn btn-success">Guardar</button> <a class="btn btn-secondary" href="<%=request.getContextPath()%>/MascotaServlet">Cancelar</a>
</form></div></body></html>