package br.com.alura.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by Everton Lima on 30/01/2018.
 */

public class AlunoDao extends SQLiteOpenHelper
{

    public AlunoDao(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql="create table aluno(id integer primary key,nome text,endereco text,email text,celular text,serie text,avaliacao real);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       String sql="DROP TABLE IF EXISTIS aluno";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        //criando conent value, objeto aonde vai salvar os dados para a coluna aonde vai cada elemento do objeto aluno correspodente
        ContentValues dados = getContentValuesAluno(aluno);
        //inserindo o cantentvalue carregado com o objeto alunos
        db.insert("aluno",null,dados);

    }

    @NonNull
    private ContentValues getContentValuesAluno(Aluno aluno) {
        ContentValues dados=new ContentValues();
        dados.put("nome",aluno.getNome());
        dados.put("endereco",aluno.getEndereco());
        dados.put("email",aluno.getEmail());
        dados.put("celular",aluno.getTelefone());
        dados.put("serie",aluno.getSerie());
        dados.put("avaliacao",aluno.getNotas());
        return dados;
    }

    public List<Aluno> buscarAlunos() {
        String sql="select * from aluno;";
        SQLiteDatabase db=getReadableDatabase();
        Cursor c= db.rawQuery(sql,null);
        List<Aluno>listaAlunos=new ArrayList<Aluno>();

        while(c.moveToNext())
        {
            Aluno AlunoTemp=new Aluno();
          AlunoTemp.setId(c.getLong(c.getColumnIndex("id")));
          AlunoTemp.setNome(c.getString(c.getColumnIndex("nome")));
          AlunoTemp.setTelefone(c.getString(c.getColumnIndex("celular")));
          AlunoTemp.setEndereco(c.getString(c.getColumnIndex("endereco")));
          AlunoTemp.setEmail(c.getString(c.getColumnIndex("email")));
          AlunoTemp.setNotas(c.getDouble(c.getColumnIndex("avaliacao")));
          AlunoTemp.setSerie(c.getString(c.getColumnIndex("serie")));
           listaAlunos.add(AlunoTemp);
        }
        c.close();
        return listaAlunos;
    }

    public void removerAluno(Aluno aluno) {
        SQLiteDatabase db=getWritableDatabase();
        String[]paramts={aluno.getId().toString()};
        db.delete("aluno","id = ?",paramts);

    }

    public Aluno buscarAluno(Aluno alunoabucsar)
    {
        String sql="select * from aluno;";
        SQLiteDatabase db=getReadableDatabase();
        Cursor c= db.rawQuery(sql,null);
        List<Aluno>listaAlunos=new ArrayList<Aluno>();
        Aluno alunoCarregado=new Aluno();
        while(c.moveToNext())
        {
            Aluno AlunoTemp=new Aluno();

            AlunoTemp.setId(c.getLong(c.getColumnIndex("id")));
            AlunoTemp.setNome(c.getString(c.getColumnIndex("nome")));
            AlunoTemp.setTelefone(c.getString(c.getColumnIndex("celular")));
            AlunoTemp.setEndereco(c.getString(c.getColumnIndex("endereco")));
            AlunoTemp.setEmail(c.getString(c.getColumnIndex("email")));
            AlunoTemp.setNotas(c.getDouble(c.getColumnIndex("avaliacao")));
            AlunoTemp.setSerie(c.getString(c.getColumnIndex("serie")));
            if((AlunoTemp.getNome()==alunoabucsar.getNome())&&(AlunoTemp.getTelefone()==alunoabucsar.getTelefone())&&(AlunoTemp.getSerie()==alunoabucsar.getSerie())==(AlunoTemp.getEndereco()==alunoabucsar.getEndereco())&&(AlunoTemp.getEmail()==alunoabucsar.getEmail()))
            {
                alunoCarregado=AlunoTemp;
                break;
            }
            else{
                AlunoTemp=null;
                alunoCarregado=null;
            }
        }
        c.close();
        return alunoCarregado;

    }

    public void altera(Aluno aluno) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues dados = getContentValuesAluno(aluno);
        String[] parms={aluno.getId().toString()};
        db.update("aluno",dados,"id = ?",parms);
    }
}
