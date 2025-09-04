package dao;

import model.Disciplina;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAOImp implements DisciplinaDAO {
    private List<Disciplina> disciplinas = new ArrayList<>();

    @Override
    public void insert(Disciplina obj) {
        disciplinas.add(obj);
    }

    @Override
    public void update(Disciplina obj) {
        for (int i = 0; i < disciplinas.size(); i++) {
            if (disciplinas.get(i).getIdDisciplina() == obj.getIdDisciplina()) {
                disciplinas.set(i, obj);
                break;
            }
        }
    }

    @Override
    public void deleteById(int id) {
        disciplinas.removeIf(d -> d.getIdDisciplina() == id);
    }

    @Override
    public Disciplina findById(int id) {
        for (Disciplina d : disciplinas) {
            if (d.getIdDisciplina() == id) {
                return d;
            }
        }
        return null;
    }

    @Override
    public List<Disciplina> findAll() {
        return new ArrayList<>(disciplinas);
    }
}