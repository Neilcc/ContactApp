package com.zcc.contactapp.contactmodel.repository;

/**
 * ContactDataBean for json convert
 * <p>
 * {" +
 * "    \"first_name\": \"Robin\",\n" +
 * "    \"last_name\": \"Counts\",\n" +
 * "    \"avatar_filename\": \"Robin Counts.png\",\n" +
 * "    \"title\": \"Customer Service Representative\",\n" +
 * "    \"introduction\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam pulvinar neque in ullamcorper finibus. Aliquam ante orci, elementum non efficitur id, commodo ac velit. Proin non ornare neque, ac ornare odio. Nullam imperdiet tellus lacinia, semper justo vel, elementum metus. Aenean eget diam at quam dignissim varius. Nunc sed urna vehicula ipsum efficitur volutpat. Mauris vel augue ut magna tincidunt imperdiet. Integer sit amet vestibulum justo. Aenean placerat, nibh ac accumsan tincidunt, lorem arcu maximus justo, sed elementum tellus nisi id purus. Sed ac porttitor orci. Etiam et augue ullamcorper nibh mattis pharetra. Suspendisse ac mauris nec velit euismod rhoncus. Vestibulum tempor magna purus, id lacinia erat tempus eget.\"\n" +
 * "  },
 */
public class ContactDataBean {

    private String first_name;
    private String last_name;
    private String avatar_filename;
    private String title;
    private String introduction;

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getAvatarFilename() {
        return avatar_filename;
    }

    public String getTitle() {
        return title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setAvatarFilename(String avatar_filename) {
        this.avatar_filename = avatar_filename;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
