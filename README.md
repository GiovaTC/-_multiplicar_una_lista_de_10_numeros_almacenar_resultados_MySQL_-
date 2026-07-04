# -_multiplicar_una_lista_de_10_numeros_almacenar_resultados_MySQL_- :.
# Proyecto Java 21 + JDBC + MySQL:

<img width="1254" height="1254" alt="image" src="https://github.com/user-attachments/assets/60f95cb0-247a-444d-8fac-644931e5d1c6" />    

<img width="2551" height="1037" alt="image" src="https://github.com/user-attachments/assets/258bde3a-2382-42b2-9536-370b2a538cc6" />    

```

## Multiplicar una lista de 10 números y almacenar los resultados en MySQL:

Proyecto completo en **Java 21** utilizando **JDBC (sin Spring Boot)**, que realiza las siguientes operaciones:

- ✅ Solicita **10 números** al usuario.
- ✅ Multiplica cada número por **2**.
- ✅ Muestra el resultado en la consola.
- ✅ Guarda en **MySQL** el número original y el resultado de la multiplicación.
- ✅ Consulta y muestra posteriormente toda la información almacenada.

---

# Tecnologías utilizadas

- Java 21
- JDBC
- MySQL 8
- IntelliJ IDEA
- MySQL Connector/J 9.x

---

# Estructura del proyecto

```text
MultiplicarNumerosJava
│
├── lib
│   └── mysql-connector-j-9.x.x.jar
│
└── src
    │
    ├── ConexionBD.java
    ├── Numero.java
    ├── NumeroDAO.java
    └── Principal.java
```

---

# Base de datos MySQL

```sql
CREATE DATABASE multiplicacion_db;

USE multiplicacion_db;

CREATE TABLE numeros(

    id INT AUTO_INCREMENT PRIMARY KEY,

    numero DOUBLE NOT NULL,

    resultado DOUBLE NOT NULL

);
```

---

# ConexionBD.java

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/multiplicacion_db";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    public static Connection conectar() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);

    }

}
```

---

# Numero.java

```java
public class Numero {

    private int id;

    private double numero;

    private double resultado;

    public Numero() {
    }

    public Numero(double numero, double resultado) {
        this.numero = numero;
        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

}
```

---

# NumeroDAO.java

```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NumeroDAO {

    public void guardar(Numero numero) {

        String sql =
                "INSERT INTO numeros(numero,resultado) VALUES(?,?)";

        try(Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setDouble(1, numero.getNumero());
            ps.setDouble(2, numero.getResultado());

            ps.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();

        }

    }

    public void mostrarDatos(){

        String sql =
                "SELECT * FROM numeros";

        try(Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            System.out.println();

            System.out.println("========== DATOS EN MYSQL ==========");

            while(rs.next()){

                System.out.println(

                        "ID: " + rs.getInt("id") +
                        " | Numero: " + rs.getDouble("numero") +
                        " | Resultado: " + rs.getDouble("resultado")

                );

            }

        }catch(Exception e){

            e.printStackTrace();

        }

    }

}
```

---

# Principal.java

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        NumeroDAO dao = new NumeroDAO();

        List<Double> lista = new ArrayList<>();

        System.out.println("INGRESE 10 NUMEROS");

        for(int i=1;i<=10;i++){

            System.out.print("Numero " + i + ": ");

            lista.add(teclado.nextDouble());

        }

        System.out.println();

        System.out.println("===== RESULTADOS =====");

        for(Double numero : lista){

            double resultado = numero * 2;

            System.out.println(numero + " x 2 = " + resultado);

            dao.guardar(

                    new Numero(numero,resultado)

            );

        }

        dao.mostrarDatos();

        teclado.close();

    }

}
```

---

# Dependencia JDBC (Maven)

Si utiliza **Maven**, agregue la siguiente dependencia en el archivo **pom.xml**:

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.3.0</version>
</dependency>
```

---

# Ejemplo de ejecución

```text
INGRESE 10 NUMEROS

Numero 1: 5
Numero 2: 10
Numero 3: 7
Numero 4: 3
Numero 5: 8
Numero 6: 1
Numero 7: 9
Numero 8: 15
Numero 9: 20
Numero 10: 12

===== RESULTADOS =====

5.0 x 2 = 10.0
10.0 x 2 = 20.0
7.0 x 2 = 14.0
3.0 x 2 = 6.0
8.0 x 2 = 16.0
1.0 x 2 = 2.0
9.0 x 2 = 18.0
15.0 x 2 = 30.0
20.0 x 2 = 40.0
12.0 x 2 = 24.0

========== DATOS EN MYSQL ==========

ID: 1 | Numero: 5.0 | Resultado: 10.0
ID: 2 | Numero: 10.0 | Resultado: 20.0
ID: 3 | Numero: 7.0 | Resultado: 14.0
ID: 4 | Numero: 3.0 | Resultado: 6.0
ID: 5 | Numero: 8.0 | Resultado: 16.0
ID: 6 | Numero: 1.0 | Resultado: 2.0
ID: 7 | Numero: 9.0 | Resultado: 18.0
ID: 8 | Numero: 15.0 | Resultado: 30.0
ID: 9 | Numero: 20.0 | Resultado: 40.0
ID: 10 | Numero: 12.0 | Resultado: 24.0
```

---

# Funcionamiento del proyecto

1. El usuario ingresa **10 números** desde la consola.
2. Cada número es multiplicado por **2**.
3. El resultado se muestra inmediatamente en la consola.
4. Se almacena en **MySQL**:
   - El número original.
   - El resultado de la multiplicación.
5. Finalmente, se consulta la tabla **numeros** y se muestran todos los registros almacenados.

---

# Características

- ✔ Compatible con **Java 21**.
- ✔ Compatible con **IntelliJ IDEA**.
- ✔ Compatible con **MySQL 8**.
- ✔ Utiliza **JDBC puro**.
- ✔ No depende de **Spring Boot**.
- ✔ No utiliza **Hibernate**.
- ✔ Código sencillo y fácil de comprender.
- ✔ Arquitectura organizada mediante clases para conexión, entidad, acceso a datos y ejecución principal.

---

# Resultado

Al ejecutar el proyecto, cada número ingresado es procesado, multiplicado por **2**, almacenado en la base de datos **MySQL** y 
posteriormente recuperado para mostrar todos los registros guardados, demostrando el uso de **JDBC** para realizar 
operaciones de inserción y consulta en una base de datos relacional .
:. . / .    
