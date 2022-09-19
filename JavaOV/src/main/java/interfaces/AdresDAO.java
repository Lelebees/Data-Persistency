package interfaces;

import domain.Adres;
import domain.Reiziger;

import java.util.List;

public interface AdresDAO {

    public boolean save(Adres adres);
    public boolean update(Adres adres);
    public boolean delete(Adres adres);
    public List<Adres> findAll();
    public Adres findByReiziger(Reiziger reiziger);
}
