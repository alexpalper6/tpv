package modeloJuego;

public enum Generos {
    JRPG("Rol"),
    MMORPG("Rol multijugador masivo"),
    FPS("Shooter primera persona"),
    AVENTURAS("Aventuras"),
    CASUAL("Casual"),
    PLATAFORMAS("Plataformas");

    private String descripcion;

    Generos(String descirpcion) {
        this.descripcion = descirpcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
