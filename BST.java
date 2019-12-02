import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

public class BST<E extends Comparable<E>> implements Tree<E> {
  protected TreeNode<E> root;
  protected int size = 0;

  public BST() {
  }
  
  public BST(E[] objects) {
	    for (int i = 0; i < objects.length; i++)
	      add(objects[i]);
	  }

  private void add(E e) {
	// TODO Auto-generated method stub
	
}

public boolean search(E e) {
    TreeNode<E> current = root;

    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      } else if (e.compareTo(current.element) > 0) {
        current = current.right;
      } else {
        return true;
      }
    }

    return false;
  }

  public boolean insert(E e) {
    if (root == null) {
      root = createNewNode(e, null);
    } else {
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null) {
        if (e.compareTo(current.element) < 0){
          parent = current;
          current = current.left;
        } else if (e.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        } else {
          return false;
        }
      }

      if (e.compareTo(parent.element) < 0) {
        parent.left = createNewNode(e, parent);
      } else {
        parent.right = createNewNode(e, parent);
      }
    }

    size++;
    return true;
  }

  private TreeNode<E> getNode(E element) {
    TreeNode<E> current = root;

    while (current != null) {
      if (element.compareTo(current.element) < 0) {
        current = current.left;
      } else if (element.compareTo(current.element) > 0) {
        current = current.right;
      } else {
        break;
      }
    }
    if (current == null) { return null; }
    return current;
  }

  public boolean isLeaf(E element) {
    TreeNode<E> node = getNode(element);
    if (node == null) { 
    	return false; 
    	}
    return node.left == null && node.right == null;
  }

  public ArrayList<E> getPath(E e) {
    ArrayList<E> list = new ArrayList<>();
    TreeNode<E> node = getNode(e);
    while (node != null) {
      list.add(node.element);
      node = node.parent;
    }
    return list;
  }

  protected TreeNode<E> createNewNode(E e, TreeNode<E> parent) {
    TreeNode<E> node = new TreeNode<>(e);
    node.parent = parent;
    return node;
  }

  public void breadthFirst() {
    Queue<TreeNode<E>> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      TreeNode<E> node = queue.poll();
      System.out.print(node.element + " ");
      if (node.left != null) { queue.offer(node.left); }
      if (node.right != null) { queue.offer(node.right); }
    }
  }

  public void inorder() {
    inorder(root);
  }

  protected void inorder(TreeNode<E> root) {
    if (root == null) { return; }
    inorder(root.left);
    System.out.print(root.element + " ");
    inorder(root.right);
  }

  public void postorder() {
    postorder(root);
  }

  protected void postorder(TreeNode<E> root) {
    if (root == null) { return; }
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.element + " ");
  }


  public void preorder() {
    preorder(root);
  }

  protected void preorder(TreeNode<E> root) {
    if (root == null) { return; }
    System.out.print(root.element + " ");
    preorder(root.left);
    preorder(root.right);
  }

  public static class TreeNode<E extends Comparable<E>> {
    protected E element;
    protected TreeNode<E> left;
    protected TreeNode<E> right;
    protected TreeNode<E> parent;

    public TreeNode(E e) {
      element = e;
    }
  }

  public int getSize() {
    return size;
  }

  public TreeNode<E> getRoot() {
    return root;
  }

  public ArrayList<TreeNode<E>> path(E e) {
    ArrayList<TreeNode<E>> list = new ArrayList<>();
    TreeNode<E> current = root;

    while (current != null) {
      list.add(current);
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      } else if (e.compareTo(current.element) > 0) {
        current = current.right;
      } else {
        break;
      }
    }

    return list;
  }

  public boolean delete(E e) {
    TreeNode<E> parent = null;
    TreeNode<E> current = root;
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        parent = current;
        current = current.left;
      } else if (e.compareTo(current.element) > 0) {
        parent = current;
        current = current.right;
      } else {
        break;
      }
    }

    if (current == null) {
      return false;
    }

    if (current.left == null) {
      if (parent == null) {
        root = current.right;
        root.parent = null;
      } else {
        if (e.compareTo(parent.element) < 0) {
          parent.left = current.right;
        } else {
          parent.right = current.right;
        }
      }
    } else {
      TreeNode<E> parentOfRightmost = current;
      TreeNode<E> rightmost = current.left;

      while (rightmost.right != null) {
        parentOfRightmost = rightmost;
        rightmost = rightmost.right;
      }

      current.element = rightmost.element;

      if (parentOfRightmost.right == rightmost) {
        parentOfRightmost.right = rightmost.left;
      } else {
        parentOfRightmost.left = rightmost.left;
      }
    }

    size--;
    return true;
  }

  
  public Iterator<E> iterator() {
    return new InorderIterator();
  }

  private class InorderIterator implements Iterator<E> {
    private ArrayList<E> list = new ArrayList<>();
    private int current = 0;

    public InorderIterator() {
      inorder();
    }

    private void inorder() {
      inorder(root);
    }

    private void inorder(TreeNode<E> root) {
      if (root == null) { return; }
      inorder(root.left);
      list.add(root.element);
      inorder(root.right);
    }

    @Override
    public boolean hasNext() {
      if (current < list.size()) {
        return true;
      }
      return false;
    }

    @Override
    public E next() {
      return list.get(current++);
    }

    @Override
    public void remove() {
      delete(list.get(current));
      list.clear();
      inorder();
    }
  }

  public void clear() {
    root = null;
    size = 0;
  }

@Override
public boolean isEmpty() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public int compareTo(Rectangle o) {
	// TODO Auto-generated method stub
	return 0;
}

}
