package com.example.sistemadequejas.web;

import com.example.sistemadequejas.*;
import com.example.sistemadequejas.dao.UsuarioDAO;
import com.example.sistemadequejas.service.cargo.CargoSevice;
import com.example.sistemadequejas.service.estado.EstadoService;
import com.example.sistemadequejas.service.medioIngreso.MedioIngresoService;
import com.example.sistemadequejas.service.puntos.PuntoService;
import com.example.sistemadequejas.service.quejacuentahabiente.QuejaCuentaHabienteService;
import com.example.sistemadequejas.service.quejaempleado.QuejaEmpleadoService;
import com.example.sistemadequejas.service.regiones.RegionService;
import com.example.sistemadequejas.service.rol.RolService;
import com.example.sistemadequejas.service.tipoqueja.TIpoQuejaService;
import com.example.sistemadequejas.service.usuario.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller
@Slf4j
public class ControladorInicio {


    private  int incremento=0;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PuntoService puntoService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CargoSevice cargoSevice;

    @Autowired
    UsuarioDAO usuarioDAO;

    @Autowired
    private RolService rolService;

    @Autowired
    private TIpoQuejaService tIpoQuejaService;

    @Autowired
    private QuejaCuentaHabienteService quejaCuentaHabienteService;

    @Autowired
    private MedioIngresoService medioIngresoService;

    @Autowired
    private QuejaEmpleadoService quejaEmpleadoService;

    @GetMapping("/")
    public String inicio(Model model,  @AuthenticationPrincipal User user) {

        log.info("user login: "+user);
        log.info("executing the controller Spring MVC");
        return "index";
    }

    @GetMapping("/getRegions")
    public String listarDatosPorRegiones(Long id, Model model){
        var listPuntosPorRegiones = puntoService.buscaPuntosPorRegion(id);
        model.addAttribute("listPuntosPorRegiones", listPuntosPorRegiones);
        return "agregarUsuario";
    }

    @GetMapping("/agregarQuejaCuentaHabiente")
    public String agregar(QuejaCuentaHabiente quejaCuentaHabiente) {
        return "guardarQuejaCuentaHabiente";
    }

    @GetMapping("/controlQuejaCuentaHabiente")
    public String visitar() {
        return "indexIngresoDeQuejas";
    }

    @RequestMapping(value ="/guardarQueja", method =RequestMethod.POST)
    public String guardar(QuejaCuentaHabiente quejaCuentaHabiente, RedirectAttributes redirect,
                          @RequestParam(name="g-recaptcha-response") String captchaResponse
                          ){



        String url = "https://www.google.com/recaptcha/api/siteverify";
        String parametro = "?secret=6Lcc0HwUAAAAAE95NcYpjnL1eQ-EuIpViecOpWRQ&response="+captchaResponse;
        ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+parametro, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();

        if(reCaptchaResponse.isSuccess()) {
            var quejaCuentaHabientes = quejaCuentaHabienteService.listaQuejaCuentaHabiente();
            incremento = quejaCuentaHabientes.size()+1;
            String correlativo =quejaCuentaHabiente.getTipoQueja().getSiglas()+"-"+ String.valueOf(incremento)+"-2021";
            quejaCuentaHabiente.setCorrelativo(correlativo);
            quejaCuentaHabienteService.guardar(quejaCuentaHabiente);

            redirect.addFlashAttribute("message", "queja Creada! Su correlativo es: "+correlativo);

            return "redirect:/agregarQuejaCuentaHabiente";
        } else {
            redirect.addFlashAttribute("message", "Error al enviar su queja,  verifique el Captcha");

            return "redirect:/agregarQuejaCuentaHabiente";
        }

    }


    @ModelAttribute
    public void addAttributes(Model model) {

        var regiones = regionService.listaRegions();
        var estado = estadoService.listaEstados();
        var cargos = cargoSevice.cargoList();
        var puntos = puntoService.listaPuntos();
        var roles = rolService.listaRoles();
        var usuarios = usuarioService.listaUsuarios();
        var tipoquejas = tIpoQuejaService.listaTiposQueja();
        var medioIngreso =  medioIngresoService.listaMediosIngresos();
        var quejaEmpleados = quejaEmpleadoService.listaQuejaEmpleados();
        var quejaCuentaHabientes = quejaCuentaHabienteService.listaQuejaCuentaHabiente();


        model.addAttribute("regiones", regiones);
        model.addAttribute("estado", estado);
        model.addAttribute("cargos", cargos);
        model.addAttribute("puntos", puntos);
        model.addAttribute("roles", roles);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("tipoquejas", tipoquejas);
        model.addAttribute("tipoMedioIngreso",medioIngreso);
        model.addAttribute("quejaCuentaHabientes",quejaCuentaHabientes);
        model.addAttribute("quejaEmpleados",quejaEmpleados);



        model.addAttribute("totalQueja",quejaCuentaHabientes.size()+quejaEmpleados.size());
        model.addAttribute("totalUsuarios",usuarios.size());
        model.addAttribute("totalRegiones",regiones.size());
        model.addAttribute("totalPuntos",puntos.size());

    }





    @GetMapping("/editarUsuario/{idUsuario}")
    public String editar(Usuario usuario, Model model){
        usuario = usuarioService.econtrarUsuarioPorId(usuario);
        model.addAttribute("usuario", usuario);
        return "agregarUsuario";
    }

    @GetMapping("/eliminarUsuario")
    public String eliminar(Usuario usuario, RedirectAttributes redirect){
         usuario = usuarioService.econtrarUsuarioPorId(usuario);
         Estado estado = new Estado();
         estado.setIdEstado(0L);
         usuario.setEstado(estado);
         usuarioService.removeUsuario(usuario);
        redirect.addFlashAttribute("mensaje","el Usuario "+ usuario.getNombre()+" ha sido eliminado correctamente!! ");
        return "redirect:/listaUsuarios";
    }


    @GetMapping("/agregarPunto")
    public String agregar(Punto punto) {
        return "guardarPunto";
    }

    @GetMapping("/editar/{idPunto}")
    public String editar(Punto punto, Model model){
        punto = puntoService.encontrarPunto(punto);
        model.addAttribute("punto", punto);
        return "guardarPunto";
    }

    @GetMapping("/eliminar")
    public String eliminar(Punto punto, RedirectAttributes redirect){

        punto = puntoService.encontrarPunto(punto);
        Estado estado = new Estado();
        estado.setIdEstado(0L);
        punto.setEstado(estado);
        puntoService.eliminar(punto);
        redirect.addFlashAttribute("mensaje","elimindo correctamente el punto: "+punto.getNombre());
        return "redirect:/listaPuntos";

    }

    @PostMapping("/guardarPunto")
    public String guardar(@Valid Punto punto, Errors errors){
        if (errors.hasErrors()){
            return "guardarPunto";
        }
        puntoService.guardar(punto);
        return "redirect:/listaPuntos";
    }

    @GetMapping("/agregarUsuario")
    public String agregarUsuario(Usuario usuario) {
        return "agregarUsuario";
    }

    @RequestMapping(value = "/guardarUsuario", method = RequestMethod.POST)
    public String guardar( Usuario usuario, Errors errors, RedirectAttributes redirect){

//       Usuario UsuarioE =   usuarioService.encontrarUsuarioPorDpi(usuario.getDpi());
        if (errors.hasErrors()){
//            redirect.addFlashAttribute("mensaje","elimindo correctamente el punto: "+usuario.getNombre());
            return "redirect:/agregarUsuario";

        }

            usuario.setPassword(EncriptarPassword.encode(usuario.getPassword()));
            usuarioService.addUsuario(usuario);
            return "redirect:/listaUsuarios";
    }


    @GetMapping("/listaQuejaCuentaHabiente")
    private String mostrarDatosQuejasC(){
        return "listQuejasCuentaHabiente";
    }

    @GetMapping("/listaQuejasEmpleado")
    private String mostrarDatosQuejasE(){
        return "listQuejaEmpleados";
    }


    @GetMapping("/listaUsuarios")
    private String mostrarDatosUsuarios(){
        return "listUsuarios";
    }

    @GetMapping("/listaPuntos")
    private String mostrarDatosPuntos(){
        return "listaPuntos";
    }

    @GetMapping("/agregarQuejaEmpleado")
    public String agregar(QuejaEmpleado quejaEmpleado) {
        return "guardarQuejaEmpleado";
    }

    @PostMapping("/guardarQuejaEmpleado")
    public String guardar(@Valid QuejaEmpleado quejaEmpleado, Errors errors, @AuthenticationPrincipal User user, RedirectAttributes redirect){
        if (errors.hasErrors()){
            return "guardarQuejaEmpleado";
        }

        Usuario usuario = new Usuario();
        usuario = usuarioDAO.findByUsername(user.getUsername());
        quejaEmpleado.setUsuario(usuario);

        
        var quejaEmpleados = quejaEmpleadoService.listaQuejaEmpleados();
        //code here how to set a new correlative
        incremento = quejaEmpleados.size()+1;
        String correlativo =quejaEmpleado.getTipoQueja().getSiglas()+"-"+ String.valueOf(incremento)+"-2021";
        quejaEmpleado.setCorrelativo(correlativo);
        quejaEmpleadoService.guardar(quejaEmpleado);
        redirect.addFlashAttribute("message", "queja Creada! el correlativo es: "+correlativo);
        return "redirect:/agregarQuejaEmpleado";
    }

    @GetMapping("/indexReceptor")
    public String indexReceptor() {
        return "indexEmpleadoQueja";
    }


    @GetMapping("/agregarTipoQueja")
    public String agregar(TipoQueja tipoQueja) {
        return "guardarTipoQueja";
    }

    @PostMapping("/guardarTipoQueja")
    public String guardar( TipoQueja tipoQueja, RedirectAttributes redirect){

         TipoQueja tipoQuejaExiste = this.tIpoQuejaService.buscarTipoQuejaPorSigla(tipoQueja.getSiglas());

        if(tipoQuejaExiste!=null) {
            redirect.addFlashAttribute("mensaje", " “Las siglas para la queja que ingresó, ya fueron registradas previamente en el sistema, verifique!“ ");
            tipoQuejaExiste = new TipoQueja();
            return "redirect:/agregarTipoQueja";
        }

            tIpoQuejaService.guardarTipoQueja(tipoQueja);
            redirect.addFlashAttribute("mensaje2"," “El tipo de queja: "+ tipoQueja.getSiglas()+"-"+ tipoQueja.getDescripcion()+" ,fue guardado correctamente” ");
            return "redirect:/agregarTipoQueja";

    }

    @PostMapping("/actualizarTipoQueja")
    public String actualizar(TipoQueja tipoQueja, RedirectAttributes redirect){

        tIpoQuejaService.guardarTipoQueja(tipoQueja);
        redirect.addFlashAttribute("mensaje2"," “Datos actualizados!!” ");
        return "redirect:/listaTipoQuejas";


    }




    @GetMapping("/editarTipo/{idTipo}")
    public String editar(TipoQueja tipoQueja, Model model){
        tipoQueja = tIpoQuejaService.buscarTipoQueja(tipoQueja);
        model.addAttribute("tipoQueja", tipoQueja);
        return "editarTipoQueja";
    }

    @GetMapping("/eliminarTipoQueja")
    public String eliminar(TipoQueja tipoQueja, RedirectAttributes redirect){
        tipoQueja = tIpoQuejaService.buscarTipoQueja(tipoQueja);
        Estado estado = new Estado();
        estado.setIdEstado(0L);
        tipoQueja.setEstado(estado);
        tIpoQuejaService.guardarTipoQueja(tipoQueja);
        redirect.addFlashAttribute("mensaje","eliminado correctamente!! ");
        return "redirect:/listaTipoQuejas";
    }


    @GetMapping("/listaTipoQuejas")
    private String mostrarDatosTipoQueja(){
        return "listTipoQuejas";
    }

   


}
