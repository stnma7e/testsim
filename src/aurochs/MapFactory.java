package aurochs;

import java.io.File;
import java.io.IOException;

import nu.xom.*;

public class MapFactory {
	private static MapFactory instance;
	private MapInstance mapconstruction;
	
	protected MapFactory() {
		
	}
	public static MapFactory getInstance() {
		if (instance == null) {
			instance = new MapFactory();
		}
		return instance;
	}
	
	public void parseXML(String fileOnDisk) throws ParsingException, IOException {
		Builder parser = new Builder();
		Document doc = parser.build(new File(fileOnDisk));
		
		Element root = doc.getRootElement();
		createMapInstance(root, 0);
	}
	private void createMapInstance(Node current, int depth) {
		printSpaces(depth);
		String data = "";
		
		if(current instanceof Element) {
			Element temp = (Element)current;
			data = ": " + temp.getQualifiedName();
			for (int i = 0; i < temp.getAttributeCount(); i++) {
				Attribute attribute = temp.getAttribute(i);
				String attValue = attribute.getValue();
				attValue = attValue.replace('\n', ' ').trim();
				
				if (attValue.length() >= 20) {
					attValue = attValue.substring(0, 17) + "..."; 
				}
				
				data += "\r\n    ";
				data += attribute.getQualifiedName();
				data += "=";
				data += attValue;
			}
		}
		System.out.println(current.getClass().getName() + data);
		for (int i = 0; i < current.getChildCount(); i++) {
			createMapInstance(current.getChild(i), depth+1);
		}
	}
	private void printSpaces(int depth) {
		for (int i = 0; i <= depth; i++) {
			System.out.print(' ');
		}
	}
}
