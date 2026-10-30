package com.veterinaria.servlets;

import com.veterinaria.beans.Mascota;
import com.veterinaria.beans.Especie;
import com.veterinaria.beans.Veterinario;
import com.veterinaria.beans.Dueno;
import com.veterinaria.dao.MascotaDao;
import com.veterinaria.dao.EspecieDao;
import com.veterinaria.dao.VeterinarioDao;
import com.veterinaria.dao.DuenoDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MascotaServlet", value = "/MascotaServlet")
public class MascotaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "lista";
        MascotaDao dao = new MascotaDao();
        try {
            if (action.equals("formCrear")) {
                request.setAttribute("listaEspecies", new EspecieDao().listarEspecies());
                request.setAttribute("listaVeterinarios", new VeterinarioDao().listarVeterinarios());
                request.setAttribute("listaDuenos", new DuenoDao().listarDuenos());
                request.getRequestDispatcher("/WEB-INF/vistas/nuevaMascota.jsp")
                        .forward(request, response);
            } else if (action.equals("lista")) {
                String especieId = request.getParameter("especieId");
                String veterinarioId = request.getParameter("veterinarioId");
                request.setAttribute("lista", dao.listarMascotas(especieId, veterinarioId));
                request.setAttribute("listaEspecies", new EspecieDao().listarEspecies());
                request.setAttribute("listaVeterinarios", new VeterinarioDao().listarVeterinarios());
                request.setAttribute("especieSeleccionada", especieId);
                request.setAttribute("veterinarioSeleccionado", veterinarioId);
                request.getRequestDispatcher("/WEB-INF/vistas/listaMascotas.jsp")
                        .forward(request, response);
            } else {
                response.sendError(400, "Accion no reconocida");
            }
        } catch (SQLException | NumberFormatException ex) {
            throw new ServletException("Error al consultar mascotas", ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        MascotaDao dao = new MascotaDao();
        try {
            if ("crear".equals(action)) {
                Mascota mascota = new Mascota();
                mascota.setNombre(request.getParameter("nombre"));
                mascota.setEdad(Integer.parseInt(request.getParameter("edad")));
                mascota.setPeso(Double.parseDouble(request.getParameter("peso")));

                Especie especie = new Especie();
                especie.setIdespecie(Integer.parseInt(request.getParameter("especieId")));
                mascota.setEspecie(especie);

                Veterinario veterinario = new Veterinario();
                veterinario.setIdveterinario(Integer.parseInt(request.getParameter("veterinarioId")));
                mascota.setVeterinario(veterinario);

                Dueno dueno = new Dueno();
                dueno.setIddueno(Integer.parseInt(request.getParameter("duenoId")));
                mascota.setDueno(dueno);

                dao.setMascota(mascota);
                dao.crear();
            } else if ("borrar".equals(action)) {
                dao.setIdMascota(Integer.parseInt(request.getParameter("id")));
                dao.borrar();
            } else {
                response.sendError(400, "Accion no reconocida");
                return;
            }
            response.sendRedirect(request.getContextPath() + "/MascotaServlet");
        } catch (SQLException | NumberFormatException ex) {
            throw new ServletException("No se pudo realizar la operacion", ex);
        }
    }
}
