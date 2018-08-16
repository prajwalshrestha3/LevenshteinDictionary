import java.util.*;
public class Levenshtein {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int row;		//int variables
		int col;
        
		Scanner firstW = new Scanner(System.in);		//scanner to enter first word
		System.out.println("Enter first word: ");
		String firstWord = firstW.next();

		Scanner secondW = new Scanner(System.in);		//scanner to enter second word
		System.out.println("Enter second word: ");
		String secondWord = secondW.next();

		if(firstWord.length() > secondWord.length()){		//this if else statement is made so that the longer word can be the top row and smaller word can be the first column
			row = secondWord.length()+1;		//+1 because the first row and first column only displays the number of characters.
			col = firstWord.length()+1;
		}else{
			row = firstWord.length()+1;
			col = secondWord.length()+1;
		}

		int [][] array  = new int [row][col];		//2d array declared with length of the words.
		for(int i = 0;i < row;i++){		//2d array is filled with 0's except for the first row and the column
			for(int j = 0;j < col;j++){
				array[i][j]=i;
				if(i == 0 && j >= 1){
					array[i][j] = j;
				}else if(i > 0 && j > 0){
					array[i][j] = 0;
				}
			}
		}

		for(int i = 0;i < row;i++){		//the 2d array returned from the calculations function is printed
			for (int j = 0;j < col;j++){
				System.out.print(calculations(firstWord,secondWord,array,col,row)[i][j]+"\t");
			}
			System.out.println();
		}

	}
	public static int [][] calculations(String first,String second,int [][] array2d,int longWord,int shortWord){	//calculations function which takes the first word,second word the 2d array, the longer word between and the shorter word as well

		int [] str1 = new int[longWord-1];		//two arrays are declared to store the characters of the first word and the second word.
		int [] str2 = new int[shortWord-1];

		String temp;
		if(first.length() < second.length()){		//this if statement changes the value of first with the second when the first word that is entered is smaller than the second so that the loop works properly
			temp = first;
			first = second;
			second = temp;
		}

		for(int i = 0;i < str1.length;i++){		//the arrays are filled with the character values of the first and the second word
			str1[i] = first.charAt(i);
		}

		for(int i = 0;i < str2.length;i++){
			str2[i] = second.charAt(i);
		}



		for(int i = 0;i < shortWord - 1;i++){		//this loop starts the new rows when the column is complete
			for(int j = 0;j < longWord - 1;j++){		//this loop goes through each column when it is the end of the column new row starts
				if(str2[i] == str1[j]){		//checks if the characters are the same
					array2d[i + 1][j + 1] = array2d[i][j];
				}else{ 		//if they are not the same then the smallest value of the three numbers is saved in in variable minimum
					int minimum = Math.min(array2d[i + 1][j],Math.min(array2d[i][j + 1],array2d[i][j])) + 1;
					array2d[i + 1][j + 1] = minimum;		//minimum value is set to the respective index
				}
			}
			//System.out.println(Arrays.toString(array2d));
		}
		return array2d;		//returns a 2d array
	}
}
