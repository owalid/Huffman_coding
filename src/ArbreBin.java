
public class ArbreBin {

	public int info;
	public ArbreBin fg, fd;
	public boolean vide;
	
	public ArbreBin(){
		vide=true;
	}
	
	public ArbreBin(int val, ArbreBin fg, ArbreBin fd){
		this.info = val;
		this.fg = fg;
		this.fd = fd;
		vide = false;
	}

	public int getInfo() {
		return info;
	}

	public ArbreBin getFg() {
		if(!this.isVide()){
			return fg;
		}
		else{
			return this;
		}	
	}

	public ArbreBin getFd() {
		if(!this.isVide()){
			return fd;
		}
		else{
			return this;
		}
		
	}

	public boolean isVide() {
		return vide;
	}
}