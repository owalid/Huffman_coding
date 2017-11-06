
public class HuffNoeud extends ArbreHuff{
	ArbreHuff gauche, droit;
	
	public HuffNoeud(ArbreHuff fd, ArbreHuff fg) {
		super(fd.couple, fg.couple);

	}

}
