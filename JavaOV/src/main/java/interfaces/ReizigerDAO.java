package interfaces;

import domain.Reiziger;

import java.util.List;

public interface ReizigerDAO {

    public boolean save(Reiziger reiziger);
    public boolean update(Reiziger reiziger);
    public boolean delete(Reiziger reiziger);
    public Reiziger findByID(int id);
    public List<Reiziger> findByGbdatum(String datum);
    public List<Reiziger> findAll();
}
