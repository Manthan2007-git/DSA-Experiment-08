import java.util.Scanner;

public class Main {

    static class Node {
        int rollNo;
        Node left;
        Node right;

        Node(int rollNo) {
            this.rollNo = rollNo;
            left = null;
            right = null;
        }
    }

    static Node root = null;

    // Insert a roll number
    static Node insert(Node root, int rollNo) {

        if (root == null) {
            return new Node(rollNo);
        }

        if (rollNo < root.rollNo) {
            root.left = insert(root.left, rollNo);
        } else if (rollNo > root.rollNo) {
            root.right = insert(root.right, rollNo);
        } else {
            System.out.println("Roll number already exists.");
        }

        return root;
    }

    // Search for a roll number
    static boolean search(Node root, int rollNo) {

        if (root == null) {
            return false;
        }

        if (root.rollNo == rollNo) {
            return true;
        }

        if (rollNo < root.rollNo) {
            return search(root.left, rollNo);
        } else {
            return search(root.right, rollNo);
        }
    }

    // Find the smallest value in a subtree
    static Node findMin(Node root) {

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    // Delete a roll number
    static Node delete(Node root, int rollNo) {

        if (root == null) {
            return null;
        }

        if (rollNo < root.rollNo) {
            root.left = delete(root.left, rollNo);
        } else if (rollNo > root.rollNo) {
            root.right = delete(root.right, rollNo);
        } else {

            // No left child
            if (root.left == null) {
                return root.right;
            }

            // No right child
            if (root.right == null) {
                return root.left;
            }

            // Two children
            Node temp = findMin(root.right);
            root.rollNo = temp.rollNo;
            root.right = delete(root.right, temp.rollNo);
        }

        return root;
    }

    // Inorder traversal
    static void inorder(Node root) {

        if (root != null) {
            inorder(root.left);
            System.out.print(root.rollNo + " ");
            inorder(root.right);
        }
    }

    // Preorder traversal
    static void preorder(Node root) {

        if (root != null) {
            System.out.print(root.rollNo + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    // Postorder traversal
    static void postorder(Node root) {

        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.rollNo + " ");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Binary Search Tree Menu ---");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Inorder Traversal");
            System.out.println("5. Preorder Traversal");
            System.out.println("6. Postorder Traversal");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter student roll number: ");
                    int rollNo = sc.nextInt();
                    root = insert(root, rollNo);
                    System.out.println("Roll number inserted successfully.");
                    break;

                case 2:
                    System.out.print("Enter roll number to search: ");
                    rollNo = sc.nextInt();

                    if (search(root, rollNo)) {
                        System.out.println("Roll number found.");
                    } else {
                        System.out.println("Roll number not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter roll number to delete: ");
                    rollNo = sc.nextInt();

                    if (search(root, rollNo)) {
                        root = delete(root, rollNo);
                        System.out.println("Roll number deleted successfully.");
                    } else {
                        System.out.println("Roll number not found.");
                    }
                    break;

                case 4:
                    System.out.print("Inorder Traversal: ");
                    inorder(root);
                    System.out.println();
                    break;

                case 5:
                    System.out.print("Preorder Traversal: ");
                    preorder(root);
                    System.out.println();
                    break;

                case 6:
                    System.out.print("Postorder Traversal: ");
                    postorder(root);
                    System.out.println();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

        sc.close();
    }
}