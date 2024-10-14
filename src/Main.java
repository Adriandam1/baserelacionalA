import java.sql.Date;
import java.text.SimpleDateFormat;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            //Añadimso  os productos
            Metodos.insireProduto("p1","parafusos",3, "27/12/2020");
            Metodos.insireProduto("p2","cravos",4, "06/04/2020");
            Metodos.insireProduto("p3","tachas",6, "03/07/2020");

            // Listamos todolos productos
            System.out.println("Listando todos os produtos:");
            Metodos.listaProdutos();

            // Actualizar el precio del producto con código p2
            Metodos.actualizaPre("p2", 8);

            //lista producto por codigo:
            Metodos.listaProdutoPorCodigo("p2");

            // Eliminar el producto con código p3
            Metodos.eliminaProduto("p3");

            // Listar de nuevo para verificar los cambios
            System.out.println("Listando os produtos tras as modificacións:");
            Metodos.listaProdutos();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
