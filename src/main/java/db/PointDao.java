package db;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

//забирает сессию из кибернейта создает транзакцию может сохранять управляется через сервис сначала сешнфект потом поинтдао(сохранение и работа с бд) и поинт сервис
//записывает себе открытую сессию из фабрики, происходят все манипуляции, создаем транзакции и сохраняем
public class PointDao {
    //DAO = data aссess object прогуглить
    private Session session;
    public PointDao(){
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }
    public void save(PointModel point) {
        Transaction tx1 = session.beginTransaction();
        session.save(point);
        // применяем изменения к бд
        tx1.commit();
    }

    //получаем запрос
    public List<PointModel> findAll() {
        //todo это что
        List<PointModel> users = session.createQuery("From PointModel ", PointModel.class).getResultList();
        return users;
    }
}
