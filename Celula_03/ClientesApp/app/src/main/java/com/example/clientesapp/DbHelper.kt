package info.camposha.kotlinsqlite
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.clientesapp.*


/**
 * Vamos começar criando nossa classe auxiliar CRUD do banco de dados
  * baseado no SQLiteHelper.
  */
class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    /**
     * Nosso método onCreate ().
          * Chamado quando o banco de dados é criado pela primeira vez. Isto é
          * onde a criação de tabelas e a população inicial das tabelas
          * deve acontecer.
     */
    override fun onCreate(db: SQLiteDatabase) {

        val sql="CREATE TABLE $TABLE_CLIENTES ($CLIENTE_ID  INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, $CLIENTE_NOME TEXT,$CLIENTE_FONE TEXT,$CLIENTE_IDADE INTEGER)"
        db.execSQL(sql)
        Log.e("LOG","Criando")
    }
    /**
     * Vamos criar nosso método onUpgrade
          * Chamado quando o banco de dados precisa ser atualizado. A implementação deve
          * use esse método para descartar tabelas, adicionar tabelas ou fazer qualquer outra coisa que precisar
          * para atualizar para a nova versão do esquema.
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME)
        onCreate(db)
    }


}
