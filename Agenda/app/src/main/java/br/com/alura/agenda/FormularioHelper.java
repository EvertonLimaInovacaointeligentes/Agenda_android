package br.com.alura.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by edwin on 24/07/2017.
 */

public class FormularioHelper {
    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSerie;
    private final EditText campoEmail;
    private final RatingBar campoAvaliacao;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoSerie = (EditText) activity.findViewById(R.id.formulario_serie);
        campoEmail=(EditText)activity.findViewById(R.id.formulario_email);
        campoAvaliacao = (RatingBar) activity.findViewById(R.id.formulario_avaliacao);
        aluno=new Aluno();
    }

    public Aluno getAluno() {

        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSerie(campoSerie.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
        aluno.setNotas(Double.valueOf(campoAvaliacao.getProgress()));

        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSerie.setText(aluno.getSerie());
        campoEmail.setText(aluno.getEmail());
        campoAvaliacao.setProgress(aluno.getNotas().intValue());
        this.aluno=aluno;
    }
}