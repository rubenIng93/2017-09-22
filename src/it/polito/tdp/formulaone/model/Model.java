package it.polito.tdp.formulaone.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {
	
	private List<Season> stagioni;
	private FormulaOneDAO dao;
	private Map<Integer, Race> idMapRaces;
	private Graph<Race, DefaultWeightedEdge> grafo;
	
	public Model() {
		
		this.dao = new FormulaOneDAO();
		this.idMapRaces = new HashMap<>();
		
		
	}
	
	public List<Season> getStagioni(){
		
		this.stagioni = dao.getAllSeasons();
		
		return stagioni;
		
	}
	
	public void creaGrafo(Integer year) {
		
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.getRacesByYear(year, idMapRaces));
		
		for(GarePiloti gp : dao.getArchi(year)) {
			if(this.grafo.containsVertex(idMapRaces.get(gp.getIdGara1())) && this.grafo.containsVertex(idMapRaces.get(gp.getIdGara2())))
				Graphs.addEdgeWithVertices(grafo, idMapRaces.get(gp.getIdGara1()), idMapRaces.get(gp.getIdGara2()), gp.getPilotiAllarrivo());
		}
		
		System.out.println("Grafo creato!!!\n#vertici: "+grafo.vertexSet().size()+"\n#archi: "+grafo.edgeSet().size());
		
	}
	
	public List<GarePiloti> getMaxPeso() {
		
		Double max = Double.MIN_VALUE;
		List<GarePiloti> result = new ArrayList<>();
		
		for(DefaultWeightedEdge arco : this.grafo.edgeSet()) {
			if(grafo.getEdgeWeight(arco) > max) {
				max = grafo.getEdgeWeight(arco);
			}
		}
		
		for(DefaultWeightedEdge arco : this.grafo.edgeSet()) {
			if(grafo.getEdgeWeight(arco) == max) {
				GarePiloti gp = new GarePiloti(grafo.getEdgeSource(arco).getRaceId(), grafo.getEdgeTarget(arco).getRaceId(), max.intValue());
				result.add(gp);
			}
		}
		
		return result;
		
		
	}


}
