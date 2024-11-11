package com.alura.conversormoneda.principal;

import java.util.Map;

public record Monedas(String base_code, Map<String, Double> conversion_rates) {

}
