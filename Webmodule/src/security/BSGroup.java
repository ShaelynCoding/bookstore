package security;

import java.io.Serializable;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class BSGroup extends BSPrincipal implements Group, Serializable
{


    private static final long serialVersionUID = 1L;
    private Set<Principal> members = new HashSet<Principal>();

    public BSGroup(String name)
    {
        super(name);
    }

    public boolean addMember(Principal user)
    {
        return members.add(user);
    }

    public boolean removeMember(Principal user)
    {
        return members.remove(user);
    }

    public boolean isMember(Principal member)
    {
        return members.contains(member);
    }

    public Enumeration<? extends Principal> members()
    {
        return Collections.enumeration(members);
    }

    //auto-generate
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BSGroup bsGroup = (BSGroup) o;

        return members.equals(bsGroup.members);

    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + members.hashCode();
        return result;
    }
}
