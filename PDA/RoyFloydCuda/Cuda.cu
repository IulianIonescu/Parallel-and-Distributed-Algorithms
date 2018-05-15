#include<iostream>
#include "cuda_runtime.h"
#include "device_launch_parameters.h"
using namespace std;

#define N 5
#define INF 99999


// Device code
__global__ void RoyFloyd(int Matrice[N][N])
{
	int i = threadIdx.x;
	int j = threadIdx.y;

    if(A[procid][k] + A[k][j] < A[procid][j])
		A[procid][j] = A[procid][k] + A[k][j];
}
            
// Host code
int main()
{
   size_t size = N * N * sizeof(int);

    // Allocate input vectors h_A and h_B in host memory

    // Initialize input vectors
	int h_A[N][N] = {
		0,3,9,8,3,
		5,0,1,4,2,
		6,6,0,4,5,
		2,9,2,0,7,
		7,9,3,2,0,
	};
	
    // Allocate vectors in device memory
    int* d_A;
    cudaMalloc(&d_A, size);
	
    // Copy vectors from host memory to device memory
    cudaMemcpy(d_A, h_A, size, cudaMemcpyHostToDevice);

    // Invoke kernel
    dim3 threadsPerBlock(N, N);
	int numBlocks= 1;

	for(int k = 0; k < N; k++)
	{
		VecAdd<<<numBlocks, threadsPerBlock>>>(d_A, k);

	}
		
    // Copy result from device memory to host memory
    // h_C contains the result in host memory
    cudaMemcpy(h_A, d_A, size, cudaMemcpyDeviceToHost);

    // Free device memory
    cudaFree(d_A);
            
    // Free host memory
    for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			if (h_A[i][j] == INF)
				cout << "INF ";
			else
				cout << h_A[i][j] << " ";
		}
		cout << endl;
}

}