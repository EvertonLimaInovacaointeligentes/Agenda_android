package br.com.alura.agenda;

import android.content.Intent;
import android.net.Uri;
import android.provider.Browser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.alura.agenda.dao.AlunoDao;
import br.com.alura.agenda.modelo.Aluno;

public class ListaAlunos extends AppCompatActivity {

    private ListView visao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        visao= (ListView) findViewById(R.id.lista_novo);
        visao.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                  Aluno aluno=(Aluno)visao.getItemAtPosition(position);
                Toast.makeText(ListaAlunos.this,"Aluno: "+aluno.getNome()+", entrou",Toast.LENGTH_SHORT).show();
                Intent vaiParaFormulario=new Intent(ListaAlunos.this,FormularioActivity.class);

                vaiParaFormulario.putExtra("aluno",aluno);
                startActivity(vaiParaFormulario);
            }
        });
        Button btNovoAluno=(Button)findViewById(R.id.lista_btNovoAluno);
        btNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastro= new Intent(ListaAlunos.this,FormularioActivity.class);
                startActivity(cadastro);
            }
        });
        registerForContextMenu(visao);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
        AlunoDao dao=new AlunoDao(this);
        List<Aluno> alunos= dao.buscarAlunos();
        dao.close();


        ArrayAdapter<Aluno> adapter=new ArrayAdapter<Aluno>(this,android.R.layout.simple_list_item_1,alunos);
        visao.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
        final Aluno aluno=(Aluno)visao.getItemAtPosition(info.position);

        MenuItem ligar=menu.add("Ligar para Aluno");
        Intent itentLigar=new Intent(Intent.ACTION_CALL);
        itentLigar.setData(Uri.parse("tel:"+aluno.getTelefone()));
        ligar.setIntent(itentLigar);
        //acessando endereco de cadastro pelo mapa
        MenuItem mapa=menu.add("Visitar Mapa");
        Intent intentMapa=new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?q="+aluno.getEndereco()+", pernamcubo, brasil"));
        mapa.setIntent(intentMapa);

        //enviar sms
        MenuItem sms=menu.add("Enviar SMS");
        Intent intentSMS=new Intent(Intent.ACTION_VIEW);
        intentSMS.setData(Uri.parse("sms:"+aluno.getTelefone()));
        sms.setIntent(intentSMS);

        //visitar site aluno
        MenuItem visita=menu.add("Visitar Site");
        Intent nav=new Intent(Intent.ACTION_VIEW);
        nav.setData(Uri.parse("http://"+aluno.getEmail()));
        visita.setIntent(nav);
        //opcao de remover
        MenuItem deletar= menu.add("Remover");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
           @Override
            public boolean onMenuItemClick(MenuItem item)
           {

               AlunoDao dao=new AlunoDao(ListaAlunos.this);
               dao.removerAluno(aluno);
               dao.close();
               carregarLista();
               Toast.makeText(ListaAlunos.this,"Aluno: "+aluno.getNome()+", Removido",Toast.LENGTH_SHORT).show();
               return false;
           }
        });

    }
}
