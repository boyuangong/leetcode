# NumberOfIslands
* use bfs search
* a variable x for no. of islands
* create 2d array tracking the status whether a land has been searched
* start from the {0,0}, skip if the land has been searched
* for each point:
	x ++ (because it's a new island never been searched)
 * search points around {-1, 0} {+1, 0} {0, +1} {0, -1}. If searched, skipped, otherwise, search that point(recursive) until hits island. 
* return x