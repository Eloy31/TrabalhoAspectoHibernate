package controller;

import java.util.List;

public interface IDAO {
 public boolean inserir(Object obj);
 public boolean apagar(Object obj);
 public boolean alterar(Object obj);
 public List buscarTodos(Object obj);
 public List buscar(String filtro, Object value, boolean ifvalueString ,Object obj);
 public Object buscar(Object obj, int id);
 public Object validate(Object obj);
}
