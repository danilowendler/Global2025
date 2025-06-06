package br.com.fiap.bean;

/**
 * Classe utilitária para realizar cálculos de distância geográfica.
 */
public class CalculadoraDistancia {

    private static final int RAIO_TERRA_KM = 6371;

    /**
     * Construtor privado para prevenir a instanciação da classe utilitária.
     */
    private CalculadoraDistancia() {
    }

    /**
     * Calcula a distância em linha reta (aproximada) entre dois pontos geográficos
     * (latitude/longitude) usando a fórmula de Haversine.
     * @param lat1 Latitude do primeiro ponto, em graus decimais.
     * @param lon1 Longitude do primeiro ponto, em graus decimais.
     * @param lat2 Latitude do segundo ponto, em graus decimais.
     * @param lon2 Longitude do segundo ponto, em graus decimais.
     * @return A distância entre os dois pontos em quilômetros.
     */
    public static double calcular(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RAIO_TERRA_KM * c;
    }
}