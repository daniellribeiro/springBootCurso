package com.github.daniellribeiro.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.daniellribeiro.domain.entity.Cliente;
import com.github.daniellribeiro.domain.entity.Pedido;

@Repository
public interface Clientes extends JpaRepository<Cliente, Integer /*Id*/>{

	//List<Cliente> findByNomeLike(String nome);
	
	@Query(value = "select c from Cliente c where c.nome like :nome")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	
	@Query(value="SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos p where c.id = :id")
	Cliente todosOsPedidosDoCliente(@Param("id") Integer id);
	
//	List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);
//	
//	Cliente findOneByNome(String nome);
//	
//	boolean existsByNome(String nome);
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//	
//	private final String SELECT_NOME = "select c from Cliente c where c.nome like :nome";
//
//	@Autowired
//	private EntityManager entityManager;
//
//	@Transactional
//	public void deletar(Cliente cliente) {
//		if(!entityManager.contains(cliente))
//			cliente = entityManager.merge(cliente);
//		
//		entityManager.remove(cliente);
//	}
//
//	@Transactional
//	public void atualizar(Cliente cliente) {
//		entityManager.merge(cliente);
//	}
//
//	@Transactional
//	public void salvar(Cliente cliente) {
//		entityManager.persist(cliente);
//	}
//
//	@Transactional(readOnly = true)
//	public List<Cliente> buscarTodos() {
//		return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
//	}
//
//	@Transactional(readOnly = true)
//	public Cliente buscarClientePorId(Integer id) {
//		return entityManager.find(Cliente.class, id);
//
//	}
//
//	@Transactional(readOnly = true)
//	public List<Cliente> buscarClientePorNome(String nome) {
//		TypedQuery<Cliente> query = entityManager.createQuery(SELECT_NOME, Cliente.class);
//		query.setParameter("nome", "%" + nome + "%");
//		return query.getResultList();
//	}
}
