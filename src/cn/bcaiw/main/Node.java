package cn.bcaiw.main;

import java.io.Serializable;

class Node implements Serializable{
	public String nodeName;
	
	public String nodeId;
	
	public String nodeLayout;
	
	public String toString(){
		return nodeName+":"+nodeId;
	}
}