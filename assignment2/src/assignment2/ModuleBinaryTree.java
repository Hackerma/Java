package assignment2;

import java.util.Collection;
import java.util.Vector;

/**
 *
 * 16108849
 */
public class ModuleBinaryTree
{
    private Tree tree;
    
    private int max(int a, int b)
    {
        return a > b ? a : b;
    }
    private int height(Tree tree)
    {
        if(tree == null)
            return -1;
        return 1 + max(height(tree.left), height(tree.right)); 
    }
    public void add(Module module)
    {
        Log log = new Log();
        int visited = 0;
        Tree ins = new Tree(module);
        if(tree == null)
        {
            tree = ins;
            log.addUpdate(module.getModuleName(),
                          visited, sizeRec(tree), height(tree));
        }
        else
        {
            Tree root = tree;
            int cmp;
            while(root != null)
            {
                cmp = 
                module.getModuleID().compareToIgnoreCase(root.mod.getModuleID());
            
                if(cmp > 0)
                {
                    if(root.right == null)
                    {
                        root.right = ins;
                        log.addUpdate(module.getModuleName(),
                                      visited, sizeRec(tree), height(tree));
                        root = null;
                    }
                    else
                    {
                        root = root.right;
                        ++visited;
                    }
                }
                else if (cmp < 0)
                {
                    if(root.left == null)
                    {
                        root.left = ins;
                        log.addUpdate(module.getModuleName(),
                                      visited, sizeRec(tree), height(tree));
                        root = null;
                    }
                    else
                    {
                        root = root.left;
                        ++visited;
                    }
                }
                else
                {
                    log.addFailUpdate(module.getModuleName(),
                                  visited, sizeRec(tree), height(tree));
                    root = null;
                }
            }
        }
    }
    
    public Module getModule(String moduleID)
    {
        Tree root = tree;
        int cmp;
        while(root != null && (cmp = 
              moduleID.compareToIgnoreCase(root.mod.getModuleID()) ) != 0)
        { 
            if(cmp > 0)
                root = root.right;
            else
                root = root.left;
        }
        if(root != null)
            return root.mod;
        return null;
    }
    
    private Collection<Module> getModulesRec(Tree tree, Collection<Module> arr)
    {
        if(tree != null)
        {
            arr.add(tree.mod);
            getModulesRec(tree.left, arr);
            getModulesRec(tree.right, arr);
            
        }
        return arr;
    }
    public Collection<Module> getModules()
    {
        Collection<Module> arr =  new Vector<Module>();
        arr = getModulesRec(tree, arr);
        return arr;
    }
    
    public void clear()
    {
        tree = null;
    }
    
    public boolean isEmpty()
    {
        return tree == null;
    }
    
    
    private Tree removeID(Tree tree)
    {
        if(tree == null)
            return null;
        
        if(tree.left == null)
            return tree.right;
        if(tree.right == null)
            return tree.left;
        
        Tree next = tree.right;
        Tree prev = null;
        for(; next.left != null; prev = next, next = next.left);
        next.left = tree.left;
        
        if(tree.right != next)
        {
            prev.left = next.right;
            next.right = tree.right;
        }
        return next;
    }

    public Module remove(String moduleID)
    {
        Tree cur = tree;
        Tree prev = null;
        int cmp;
        while(cur != null && 
             (cmp = moduleID.compareToIgnoreCase(cur.mod.getModuleID()) ) != 0) 
        {
            prev = cur;
            
            if(cmp < 0) 
                cur = cur.left;       
             else 
                cur = cur.right;
        }
        Module module = cur.mod;
        if (prev == null)
            tree = removeID(cur);
        else if (prev.left == cur) 
            prev.left = removeID(cur);   
        else 
            prev.right = (cur);
        
        return module;
    }
    
    private int sizeRec(Tree cur)
    {
        if(cur != null)
            return 1 + sizeRec(cur.left) + sizeRec(cur.right);
        
        return 0;
    }
    public int size()
    {
        return sizeRec(tree);
    }
}
