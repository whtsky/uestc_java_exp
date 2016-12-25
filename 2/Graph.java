public class Graph {
  int height;
  int width;
  boolean solid;

  Graph() {
    height = 5;
    width = 5;
    solid = true;
  }

  int getHeight() { return height; }

  void setHeight(int height) { this.height = height; }

  int getWidth() { return width; }

  void setWidth(int width) { this.width = width; }

  boolean getSolid() { return solid; }

  void setSolid(boolean solid) { this.solid = solid; }

  public void show() {}

  public static void main(String[] args) {
    Graph t = new Triangle();
    t.show();
    t = new Triangle(8, 8, false);
    t.show();
    t.setSolid(false);
    t.show();
    t = new Rectangle();
    t.show();
    t.setHeight(2);
    t.show();
    t = new Rectangle(8, 8, false);
    t.show();
    t.setWidth(3);
    t.show();
  }
}

class Triangle extends Graph{
	public Triangle(){}
	Triangle(int hei, int wid, boolean soli){
		height = hei;
		width = wid;
		solid = soli;
	}
	public void show(){
		if(this.solid == true){
			for(int i = 1; i <= this.height; i++){
				for(int j = 1; j <= i; j++){
					System.out.print("*");
				}
				System.out.println();
			}
		}
		else{
			for(int i = 1; i <= this.height; i++){
				if(i == this.height){
					for(int j = 1; j <= this.width; j++){
						System.out.print("*");
					}
					System.out.println();
				} else{
					for(int j = 1; j <= i; j++){
						if(j == 1 || j == i){
							System.out.print("*");
						}
						else
							System.out.print(" ");
					}
					System.out.println();
				}
			}
		}
		System.out.println("\n");
	}
}

class Rectangle extends Graph{
	public Rectangle(){}
	Rectangle(int hei, int wid, boolean soli){
		height = hei;
		width = wid;
		solid = soli;
	}
	public void show(){
		if(this.solid == true){
			for(int i = 1; i <= this.height; i++){
				for(int j = 1; j <= this.width; j++){
					System.out.print("*");
				}
				System.out.println();
			}
		} else{
			for(int i = 1; i <= this.height; i++){
				if(i == 1 || i == this.height){
					for(int j = 1; j <= this.width; j++){
						System.out.print("*");
					}
					System.out.println();
				}
				else{
					for(int j = 1; j <= this.width; j++){
						if(j == 1 || j == this.width){
							System.out.print("*");
						}
						else
							System.out.print(" ");
					}
					System.out.println();
				}
			}
		}
				System.out.println("\n");
	}
}
