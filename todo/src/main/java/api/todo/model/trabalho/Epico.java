package api.todo.model.trabalho;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Epico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
    private String nome;
	
	@Column
    private String descricao;
	
	@Column 
	private String tipo; //C,R,U,D,L , CRUD+L
	
	@Column 
	private String back_ou_front_end; //Back-End - Front-End
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
		        name = "epico_historia",
		        joinColumns = @JoinColumn(name = "epico_id"),
		        inverseJoinColumns = @JoinColumn(name = "historia_id")
			   )
	private List<HistoriaDeUsuario> historias= new ArrayList<HistoriaDeUsuario>();
	
	public List<HistoriaDeUsuario> addHistoria(HistoriaDeUsuario historia){
        this.historias.add(historia);
        historia.getEpicos().add(this); // Atualize a outra extremidade da relação
        return historias;
    }
}