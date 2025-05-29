package br.com.fiap.bean;

/**
 * Representa a localização de um incêndio.
 */
public class Localizacao {
    private String municipio;
    private String estado;
    private double latitude;
    private double longitude;

    public Localizacao(String municipio, String estado, double latitude, double longitude) {
        this.municipio = municipio;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getEstado() {
        return estado;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    /**
     * Retorna a localização formatada como string.
     *
     * @return String formatada da localização.
     */
    public String formatarLocalizacao() {
        return municipio + ", " + estado + " (" + latitude + ", " + longitude + ")";
    }

    @Override
    public String toString() {
        return formatarLocalizacao();
    }
}
