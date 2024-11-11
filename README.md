Conversor de Divisas en Java
Este proyecto es una aplicación de consola en Java que permite convertir montos de dinero entre distintas divisas. Utiliza la API de ExchangeRate-API para obtener las tasas de cambio actuales.

Proyecto realizado por Juan Manuel Cardozo para Alura Latam.

Características
Convierte entre diferentes divisas al ingresar el código de la moneda de origen y destino.
Permite especificar la cantidad a convertir y obtiene el valor exacto en la moneda de destino.
Implementación simple y rápida en Java, que se conecta a ExchangeRate-API para obtener datos en tiempo real.
Requisitos
Java 8 o superior
Cuenta en ExchangeRate-API para obtener la clave de la API

Configuración
Clona el repositorio:

bash
Copiar código
git clone https://github.com/tu-usuario/conversor-divisas-java.git
cd conversor-divisas-java
Crea una cuenta en ExchangeRate-API para obtener tu clave de API.

Agrega tu clave de API en el código del proyecto (por ejemplo, en una variable API_KEY en el archivo principal de la aplicación).

Uso
Ejecuta la aplicación desde la línea de comandos y sigue las instrucciones para ingresar:

Código de divisa de origen (ej., "USD").
Código de divisa de destino (ej., "EUR").
Cantidad de dinero a convertir.
La aplicación te mostrará el resultado de la conversión según la tasa de cambio actual.

Ejemplo de Ejecución
plaintext
Copiar código
Ingrese el código de la divisa de origen: USD
Ingrese el código de la divisa de destino: EUR
Ingrese la cantidad de dinero a convertir: 100
Resultado: 100 USD = 90.50 EUR
Dependencias
ExchangeRate-API: API utilizada para obtener tasas de cambio.
Notas
Este proyecto está destinado a fines educativos y no debe usarse en producción sin mejoras en el manejo de errores y seguridad.
La tasa de cambio puede variar según el plan y los límites de la API de ExchangeRate-API.
Contribuciones
¡Las contribuciones son bienvenidas! Si deseas mejorar este proyecto, por favor realiza un pull request o abre un issue.

Licencia
Este proyecto está bajo la licencia MIT.
