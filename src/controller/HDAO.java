package controller;

import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

public class HDAO implements IDAO {

    Session session;
    Object obj;

    public HDAO()
    {
        this.session = HibernateUtil.getSession();

    }

    @Override
    public boolean inserir(Object obj) {
        try{
        this.session.beginTransaction();
        this.session.saveOrUpdate(obj);
        this.session.getTransaction().commit();
        return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public Object buscar(Object obj, int id) {
        try{
        this.session.beginTransaction();
        obj = this.session.load(obj.getClass(), id);
        this.session.getTransaction().commit();
        return obj;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public List buscarTodos(Object obj) {
      try{
        this.session.beginTransaction();
        List produtos =  this.session.createCriteria(obj.getClass()).list();
        this.session.getTransaction().commit();
        return produtos;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
   public List buscar(String filtro, Object value, boolean ifvalueString, Object obj) {

       if(ifvalueString)
       { value = "%"+value+"%";}
      try{
        this.session.beginTransaction();
        List list =  this.session.createCriteria(obj.getClass()).add(Restrictions.like(filtro, value)).list();
        this.session.getTransaction().commit();
        return list;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public boolean alterar(Object obj) {
       try{
        this.session.beginTransaction();
        this.session.update(obj);
        this.session.getTransaction().commit();
        return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean apagar(Object obj) {
      try{
        this.session.beginTransaction();
        this.session.delete(obj);
        this.session.getTransaction().commit();
        return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    @Override
    public Object validate(Object obj)
    {
       try{
       this.session.beginTransaction();
       List objs = (List) session.createCriteria(obj.getClass()).add( Example.create(obj)).list();
       this.session.getTransaction().commit();
       
        return objs.get(0);
    }catch(Exception e)
       {
        return null;
       }
    }
}
