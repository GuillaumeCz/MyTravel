package fr.utt.if26.mytravel;

/**
 * Created by paf on 08/11/17.
 */

public class Page {

    private int id;
    private String title;
    private String content;
    private String summary;

    public Page(int id_pf, String title_pf, String content_pf, String summary_pf) {
        id = id_pf;
        title = title_pf;
        content = content_pf;
        summary = summary_pf;
    }

    public Page(String title_pf, String content_pf, String summary_pf) {
        title = title_pf;
        content = content_pf;
        summary = summary_pf;
    }

    public String toString() {
        return "Page : "+ getId() +
                "\n- Titre : " + getTitle() +
                "\n- Contenu : " + getContent() +
                "\n- Extrait : " + getSummary() +
                "\n";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
