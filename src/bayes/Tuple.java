package bayes;

import java.util.Arrays;
import java.util.List;

public class Tuple {
public String name;
double prob;



public Tuple(String name, double prob) {
	this.name = name;
	this.prob = prob;
}



@Override
public String toString() {
	return "Tuple [name=" + name + ", prob=" + prob + "]\n";
}



@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	long temp;
	temp = Double.doubleToLongBits(prob);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
}



@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Tuple other = (Tuple) obj;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (Double.doubleToLongBits(prob) != Double.doubleToLongBits(other.prob))
		return false;
	return true;
}




}
