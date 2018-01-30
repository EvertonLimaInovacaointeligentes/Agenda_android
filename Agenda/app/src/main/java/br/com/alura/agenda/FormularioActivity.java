package br.com.alura.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;

import br.com.alura.agenda.dao.AlunoDao;
import br.com.alura.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    //private Aluno aluno;

    // private AlunoDao dao;
    private FormularioHelper helper;
/*   Desenvolvido por Everotn Lima (81)9 9231-6097 intuito de estudo e aprimoração da ferramenta android.
    no metodo onCreate  instancia a variavel aluno depois disso carrega essa variavel e essa variavel preencher e lista
    os registros da agenda no banco de dados SQLLite.
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
       // aluno=new Aluno();
        helper=new FormularioHelper(FormularioActivity.this);
        Intent intent=getIntent();
        Aluno aluno=(Aluno)intent.getSerializableExtra("aluno");
        if(aluno!=null)
        {
            helper.preencheFormulario(aluno);
        }



    }
    //metodo com os botes de menu salvar e cacelar chamado dentro da active menu_formulario
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_formulario,menu);
        //pegando o valor aluno passado da tela principal para o carregar no formulario


/*        if(aluno!=null)
        {
            aluno= helper.getAluno();
            //preenchendo campos no formulario




        }
        */
        return super.onCreateOptionsMenu(menu);
    }
//Esse metodo cadastra os registros da agenda no banco de dados SQLLITE
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok: {


              Aluno aluno=helper.getAluno();
               AlunoDao dao=new AlunoDao(FormularioActivity.this);



                if(aluno.getId()==null) {
                    dao.insere(aluno);
                    Toast.makeText(FormularioActivity.this, "Aluno: "+aluno.getNome().toString() +", Salvo com Sucesso!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dao.altera(aluno);
                    Toast.makeText(FormularioActivity.this, "Aluno: "+aluno.getNome().toString() +", Editado com Sucesso!!", Toast.LENGTH_SHORT).show();
                }
                dao.close();
                finish();
                break;
            }
            case R.id.menu_formulario_cancelar:{
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
