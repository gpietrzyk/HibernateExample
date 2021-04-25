package pl.github.gpietrzyk.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "teams")
@NamedQuery(name = "Team.findAll", query = "select t from Team t")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Integer teamId;

    @Basic
    @Column(name = "team_name", nullable = false, length = 50)
    private String teamName;

    @OneToMany(mappedBy = "team")
    private List<User> users = new ArrayList<>();

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public void addUser(User user){
        users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(teamId, team.teamId) && Objects.equals(teamName, team.teamName) && Objects.equals(users, team.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, teamName, users);
    }
}
