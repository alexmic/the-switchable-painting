package model;

import java.util.List;

import com.google.code.morphia.annotations.*;
import com.google.code.morphia.emul.org.bson.types.ObjectId;
import cv.descriptor.FeatureVector;

@Entity
public class Painting {

	@Id private ObjectId id = new ObjectId();
	private String title;
	private String artist;
	private String paintingId;
	private int descriptorType = 1;
	private String type;
	private int[] scaleIndices = null;
	@Embedded private List<String> tags;
	@Embedded private List<FeatureVector> featureVectors;
	
	public Painting setDescriptorType(int type)
	{
		this.descriptorType = type;
		return this;
	}
	
	public int getDescriptorType()
	{
		return this.descriptorType;
	}
	
	public Painting setPaintingId(String pID)
	{
		this.paintingId = pID;
		return this;
	}
	
	public Painting setTitle(String title)
	{
		this.title = title;
		return this;
	}
	
	public Painting setArtist(String artist)
	{
		this.artist = artist;
		return this;
	}
	
	public Painting setFeatureVectors(List<FeatureVector> featureVectors)
	{
		this.featureVectors = featureVectors;
		return this;
	}
	
	public Painting setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public Painting setScaleIndices(int[] indexes)
	{
		this.scaleIndices = indexes;
		return this;
	}
	
	public Painting setTags(List<String> tagList)
	{
		this.tags = tagList;
		return this;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getArtist()
	{
		return this.artist;
	}
	
	public List<FeatureVector> getFeatureVectors()
	{
		return this.featureVectors;
	}
	
	public String getType()
	{
		return type;
	}
	
	public ObjectId getId()
	{
		return this.id;
	}
	
	public String getPaintingId()
	{
		return this.paintingId;
	}
	
	public int[] getScaleIndices()
	{
		return this.scaleIndices;
	}
	
	public List<String> getTags()
	{
		return this.tags;
	}
	
	public float getMatchScore(Painting painting, float threshold)
	{
		int matched = 0;
		double d = Double.MAX_VALUE;
		List<FeatureVector> otherFeatureVectors = painting.getFeatureVectors();
		for (FeatureVector anfv : otherFeatureVectors) {
			for (int i = 0; i < this.featureVectors.size(); ++i) {
				double vectorDistance = this.featureVectors.get(i).getVectorDistance(anfv);
				if (Double.isNaN(vectorDistance))
					continue;
				d = Math.min(d, vectorDistance);
			}
			if (d < threshold) matched++;
			d = Double.MAX_VALUE;
		}
		return (float) matched / otherFeatureVectors.size();
	}
}
