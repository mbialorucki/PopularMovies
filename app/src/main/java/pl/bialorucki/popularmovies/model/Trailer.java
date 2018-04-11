package pl.bialorucki.popularmovies.model;

import java.util.Objects;

/**
 * Created by Maciej Bialorucki on 05.04.18.
 */
public class Trailer {
    private String site;

    private String id;

    private String iso_639_1;

    private String name;

    private String type;

    private String key;

    private String iso_3166_1;

    private String size;

    public String getSite ()
    {
        return site;
    }

    public void setSite (String site)
    {
        this.site = site;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getIso_639_1 ()
    {
        return iso_639_1;
    }

    public void setIso_639_1 (String iso_639_1)
    {
        this.iso_639_1 = iso_639_1;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    public String getIso_3166_1 ()
    {
        return iso_3166_1;
    }

    public void setIso_3166_1 (String iso_3166_1)
    {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getSize ()
    {
        return size;
    }

    public void setSize (String size)
    {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "site='" + site + '\'' +
                ", id='" + id + '\'' +
                ", iso_639_1='" + iso_639_1 + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", iso_3166_1='" + iso_3166_1 + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trailer trailer = (Trailer) o;
        return Objects.equals(site, trailer.site) &&
                Objects.equals(id, trailer.id) &&
                Objects.equals(iso_639_1, trailer.iso_639_1) &&
                Objects.equals(name, trailer.name) &&
                Objects.equals(type, trailer.type) &&
                Objects.equals(key, trailer.key) &&
                Objects.equals(iso_3166_1, trailer.iso_3166_1) &&
                Objects.equals(size, trailer.size);
    }

    @Override
    public int hashCode() {

        return Objects.hash(site, id, iso_639_1, name, type, key, iso_3166_1, size);
    }
}
