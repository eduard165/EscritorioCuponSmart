package modelo.pojo;

public class Direccion {

    private Integer id_direccion;
    private String calle;
    private Integer numero;
    private String codigo_postal;
    private String colonia;
    private Integer id_municipio;
    private Integer id_estado;
    private Integer id_cliente;
    private Integer id_sucursal;
    private String empresa_rfc;
    private Integer tipo_direccion;
    private String nombre_estado;
    private String nombre_municipio;

    public Direccion() {
    }

    public Direccion(Integer id_direccion, String calle, Integer numero, String codigo_postal, String colonia, Integer id_municipio, Integer id_estado, Integer id_cliente, Integer id_sucursal, String empresa_rfc, Integer tipo_direccion, String nombre_estado, String nombre_municipio) {
        this.id_direccion = id_direccion;
        this.calle = calle;
        this.numero = numero;
        this.codigo_postal = codigo_postal;
        this.colonia = colonia;
        this.id_municipio = id_municipio;
        this.id_estado = id_estado;
        this.id_cliente = id_cliente;
        this.id_sucursal = id_sucursal;
        this.empresa_rfc = empresa_rfc;
        this.tipo_direccion = tipo_direccion;
        this.nombre_estado = nombre_estado;
        this.nombre_municipio = nombre_municipio;
    }

    public Integer getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(Integer id_direccion) {
        this.id_direccion = id_direccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public Integer getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Integer id_municipio) {
        this.id_municipio = id_municipio;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getEmpresa_rfc() {
        return empresa_rfc;
    }

    public void setEmpresa_rfc(String empresa_rfc) {
        this.empresa_rfc = empresa_rfc;
    }

    public Integer getTipo_direccion() {
        return tipo_direccion;
    }

    public void setTipo_direccion(Integer tipo_direccion) {
        this.tipo_direccion = tipo_direccion;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }

    public String getNombre_municipio() {
        return nombre_municipio;
    }

    public void setNombre_municipio(String nombre_municipio) {
        this.nombre_municipio = nombre_municipio;
    }

    @Override
    public String toString() {
        if (calle == null && numero == null && codigo_postal == null && colonia == null && nombre_estado == null && nombre_municipio == null) {
            return "calle = " + "..." + "\n numero=" + "..." + "\n codigo_postal = " + "..." + "\n colonia = " + "..." + "\n nombre_estado = " + "..." + "\n nombre_municipio = " + "...";

        } else {
            return "calle = " + calle + "\n numero=" + numero + "\n codigo_postal = " + codigo_postal + "\n colonia = " + colonia + "\n nombre_estado = " + nombre_estado + "\n nombre_municipio = " + nombre_municipio;

        }
    }

}
