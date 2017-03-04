package id.benar.benarid.models;

public class Portal {
    private String slug;
    private String name;
    private String siteUrl;

    public Portal(String slug, String name, String siteUrl) {
        this.slug = slug;
        this.name = name;
        this.siteUrl = siteUrl;
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public String getSiteUrl() {
        return siteUrl;
    }
}
