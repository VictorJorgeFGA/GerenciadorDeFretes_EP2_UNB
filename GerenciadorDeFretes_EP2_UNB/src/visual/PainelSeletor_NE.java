package visual;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class PainelSeletor_NE extends JPanel{
	
	JCheckBox velocidade;
	JCheckBox menorCusto;
	JCheckBox custoBeneficio;
	
	public PainelSeletor_NE( FlowLayout layout ) {
		setLayout( layout );
		iniciarSeletores();
		setVisible(true);
	}
	
	private void iniciarSeletores() {
		velocidade = new JCheckBox("Velocidade");
		velocidade.setToolTipText("Marque essa opção para calcular o frete mais veloz");
		velocidade.setVisible(true);
		
		menorCusto = new JCheckBox("Menor custo");
		menorCusto.setToolTipText("Marque essa opção para calcular o frete mais barato");
		menorCusto.setVisible(true);
		
		custoBeneficio = new JCheckBox("Custo beneficio");
		custoBeneficio.setToolTipText("Marque essa opção para calcular o frete que possui a hora de operação mais barata");
		custoBeneficio.setVisible(true);
		
		add( velocidade );
		add( menorCusto );
		add( custoBeneficio );
		
	}
	
	public boolean isVelocidade() {
		if( velocidade.isSelected() )
			return true;
		else
			return false;
	}
	
	public boolean isMenorCusto() {
		if( menorCusto.isSelected() )
			return true;
		else
			return false;
	}
	
	public boolean isCustoBeneficio() {
		if( custoBeneficio.isSelected() )
			return true;
		else
			return false;
	}
	
	public void limparCampos() {
		velocidade.setSelected(false);
		menorCusto.setSelected(false);
		custoBeneficio.setSelected(false);
	}
}
